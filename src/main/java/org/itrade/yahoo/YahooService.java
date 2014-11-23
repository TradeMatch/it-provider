package org.itrade.yahoo;

import com.google.common.collect.Lists;
import org.itrade.tickers.beans.Ticker;
import org.itrade.tickers.resource.TickersResource;
import org.itrade.yahoo.beans.HistoricalData;
import org.itrade.yahoo.beans.Quote;
import org.itrade.yahoo.client.YahooFinanceClient;
import org.itrade.yahoo.resource.HistoricalDataResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dmytro Podyachiy on 11/11/14.
 */
@Service
public class YahooService {
    public static final int SIZE = 100;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private YahooFinanceClient yahooFinanceClient;

    @Autowired
    private HistoricalDataResource historicalDataResource;

    @Autowired
    private TickersResource tickersResource;
    private List<Ticker> nasdaqTickers;

    @PostConstruct
    public void init() {
        logger.debug("Get tickers from DB...");
        nasdaqTickers = tickersResource.getNasdaqTickers(0, 0);
        logger.debug("Nb Tickers found: {}", nasdaqTickers.size());
    }

    public int update() {
        return update(LocalDate.now(), LocalDate.now());
    }

    public int update(LocalDate from, LocalDate to) {
        List<String> symbols = nasdaqTickers.stream()
                .map(Ticker::getSymbol)
                .collect(Collectors.toList());

        return update(symbols, from, to);
    }

    public int update(List<String> tickers, LocalDate from, LocalDate to) {
        logger.info("Updating historical data for {} tickers from {} to {} ...", tickers.size(), from, to);

        List<List<String>> partitionTickers = Lists.partition(tickers, computePartitionSize(from, to));

        int size = 0; int counter = 0;
        for (List<String> partitionTicker : partitionTickers) {
            logger.debug(" updating {}/{} tickers from {} to {} ...", partitionTicker.size() * (++counter), tickers.size(), from, to);
            HistoricalData historicalData = yahooFinanceClient.getHistoricalData(partitionTicker, from, to);

            if (historicalData.getQuery().getResults() != null) {
                List<Quote> quote = historicalData.getQuery().getResults().getQuote();
                logger.debug("  inserting {} quotes to DB", quote.size());
                historicalDataResource.upsert(quote);
                size += quote.size();
            } else {
                logger.debug("  no quotes found (WE?)");
            }
        }

        logger.info("Quotes updated: {}", size);
        return size;
    }

    public int computePartitionSize(LocalDate from, LocalDate to) {
        long days = ChronoUnit.DAYS.between(from, to);
        return (int) (SIZE / (days+1));
    }

}

