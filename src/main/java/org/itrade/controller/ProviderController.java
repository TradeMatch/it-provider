package org.itrade.controller;

import org.itrade.benzinga.beans.BenzingaRating;
import org.itrade.benzinga.resource.RatingsResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProviderController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RatingsResource ratingsResource;

    @RequestMapping("/")
    public String index() {
        logger.info("Greetings from Spring Boot!!!");
        return "Greetings from Spring Boot!!!";
    }

    @RequestMapping("/ratings")
    public List<BenzingaRating> getRatings(@RequestParam(required = false, defaultValue = "0") final int offset,
                                   @RequestParam(required = false, defaultValue = "10") final int limit) {
        return ratingsResource.getRatings(offset, limit);
    }

    @RequestMapping("/ratings/{ticker}")
    public List<BenzingaRating> getRatingsByTicker(@PathVariable("ticker") String ticker,
                                           @RequestParam(required = false, defaultValue = "0") final int offset,
                                           @RequestParam(required = false, defaultValue = "10") final int limit) {
        return ratingsResource.getRatingsByTicker(ticker, offset, limit);
    }

}
