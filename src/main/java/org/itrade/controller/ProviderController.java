package org.itrade.controller;

import kafka.producer.KeyedMessage;
import org.itrade.benzinga.BenzingaService;
import org.itrade.benzinga.beans.BenzingaRating;
import org.itrade.benzinga.kafka.KafkaRatingProducer;
import org.itrade.nasdaq.NasdaqService;
import org.itrade.yahoo.YahooService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
public class ProviderController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BenzingaService benzingaService;

    @Autowired
    private YahooService yahooService;

    @Autowired
    private NasdaqService nasdaqService;

    @Autowired
    private KafkaRatingProducer kafkaRatingProducer;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!!!";
    }

    @RequestMapping("/ratings")
    public List<BenzingaRating> getRatings(
            @RequestParam(required = false, defaultValue = "0") final int offset,
            @RequestParam(required = false, defaultValue = "10") final int limit) {
        return benzingaService.getRatings(offset, limit);
    }

    @RequestMapping("/ratings/{ticker}")
    public List<BenzingaRating> getRatingsByTicker(
            @PathVariable("ticker") String ticker,
            @RequestParam(required = false, defaultValue = "0") final int offset,
            @RequestParam(required = false, defaultValue = "10") final int limit) {
        return benzingaService.getRatings(ticker, offset, limit);
    }

    @RequestMapping("/update/ratings")
    public String updateRatings(
            @RequestParam(value = "from", required = false) final String fromStr,
            @RequestParam(value = "to", required = false) final String toStr) {

        LocalDate from = null;
        LocalDate to = null;

        if (fromStr != null)
            from = LocalDate.parse(fromStr);
        if (toStr != null)
            to = LocalDate.parse(toStr);

        int updated;
        if (from == null && to == null) {
            updated = benzingaService.updateRatings();
        } else {
            if (from == null)
                from = LocalDate.now();
            if (to == null)
                to = LocalDate.now();
            logger.debug("From {}, to {}", from, to);

            updated = benzingaService.updateRatings(from, to);
        }
        return "Ratings updated: " + updated;
    }

    @RequestMapping("/update/economics")
    public String updateEconomics(
            @RequestParam(value = "from", required = false) final String fromStr,
            @RequestParam(value = "to", required = false) final String toStr) {

        LocalDate from = null;
        LocalDate to = null;

        if (fromStr != null)
            from = LocalDate.parse(fromStr);
        if (toStr != null)
            to = LocalDate.parse(toStr);

        int updated;
        if (from == null && to == null) {
            updated = benzingaService.updateEconomics();
        } else {
            if (from == null)
                from = LocalDate.now();
            if (to == null)
                to = LocalDate.now();
            logger.debug("From {}, to {}", from, to);

            updated = benzingaService.updateEconomics(from, to);
        }
        return "Ratings updated: " + updated;
    }

    @RequestMapping("/update/historical")
    public String updateHistoricalData(
            @RequestParam(value = "from", required = false) final String fromStr,
            @RequestParam(value = "to", required = false) final String toStr) {

        LocalDate from = null;
        LocalDate to = null;

        if (fromStr != null)
            from = LocalDate.parse(fromStr);
        if (toStr != null)
            to = LocalDate.parse(toStr);

        int updated;
        if (from == null && to == null) {
            updated = yahooService.update();
        } else {
            if (from == null)
                from = LocalDate.now();
            if (to == null)
                to = LocalDate.now();
            logger.debug("From {}, to {}", from, to);

            updated = yahooService.update(from, to);
        }
        return "Quites updated: " + updated;
    }

    @RequestMapping("/update/nasdaq")
    public String updateNasdaqCompanies() {
        int updated = nasdaqService.update();
        return "Nasdaq companies updated: " + updated;
    }

    @RequestMapping("/test")
    public String kafkaTest() {
        long events = 10;
        Random rnd = new Random();

        for (long nEvents = 0; nEvents < events; nEvents++) {
            long runtime = new Date().getTime();
            String ip = "192.168.2." + rnd.nextInt(255);
            String msg = runtime + ",www.example.com," + ip;

            BenzingaRating benzingaRating = new BenzingaRating();
            benzingaRating.setTicker("AAPL");

            KeyedMessage<String, BenzingaRating> data = new KeyedMessage<>("page_visits_1", ip, benzingaRating);
            kafkaRatingProducer.send(data);
        }

        return "Kafka hello";
    }

}
