package org.itrade.controller;

import org.itrade.benzinga.BenzingaService;
import org.itrade.benzinga.beans.BenzingaRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ProviderController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BenzingaService benzingaService;

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

        int updated = 0;
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

}
