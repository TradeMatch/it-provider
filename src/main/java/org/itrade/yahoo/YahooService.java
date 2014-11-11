package org.itrade.yahoo;

import com.google.common.collect.Lists;
import org.itrade.yahoo.beans.HistoricalData;
import org.itrade.yahoo.beans.Quote;
import org.itrade.yahoo.client.YahooFinanceClient;
import org.itrade.yahoo.resource.HistoricalDataResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Dmytro Podyachiy on 11/11/14.
 */
@Service
public class YahooService {

    @Autowired
    private YahooFinanceClient yahooFinanceClient;

    @Autowired
    private HistoricalDataResource historicalDataResource;

    public void update() {
        HistoricalData historicalData = yahooFinanceClient.getHistoricalData(Lists.newArrayList("FLWS", "FCTY", "FCCY", "SRCE", "VNET", "TWOU", "DGLD", "JOBS", "EGHT", "AVHI", "SHLM", "AAON", "ASTM", "ABAX"),
                LocalDate.of(2014, 9, 1), LocalDate.of(2014, 10, 1));

        List<Quote> quote = historicalData.getQuery().getResults().getQuote();
        historicalDataResource.upsert(quote);
    }

}

