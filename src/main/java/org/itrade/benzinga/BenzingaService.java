package org.itrade.benzinga;

import org.itrade.benzinga.beans.BenzingaEconomics;
import org.itrade.benzinga.beans.BenzingaRating;
import org.itrade.benzinga.beans.BenzingaRatings;
import org.itrade.benzinga.client.BenzingaEconomicsClient;
import org.itrade.benzinga.client.BenzingaRatingsClient;
import org.itrade.benzinga.kafka.KafkaRatingClient;
import org.itrade.benzinga.resource.EconomicsResource;
import org.itrade.benzinga.resource.RatingsResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Dmytro Podyachiy on 08/11/14.
 */
@Service
public class BenzingaService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RatingsResource ratingsResource;

    @Autowired
    private EconomicsResource economicsResource;

    @Autowired
    private BenzingaRatingsClient benzingaRatingsClient;

    @Autowired
    private BenzingaEconomicsClient benzingaEconomicsClient;

    @Autowired
    private KafkaRatingClient kafkaRatingClient;

    public List<BenzingaRating> getRatings(int offset, int limit) {
        return ratingsResource.getRatings(offset, limit);
    }

    public List<BenzingaRating> getRatings(String ticker, int offset, int limit) {
        return ratingsResource.getRatingsByTicker(ticker, offset, limit);
    }

    /**
     * Update Ratings starting from latest timestamp
     *
     * @return number of updated ratings
     */
    public int updateRatings() {
        logger.debug("Looking for latest timestamp...");
        long latestTimestamp = ratingsResource.getLatestTimestamp();

        // If no latest timestamp found, update last day
        if (latestTimestamp == -1) {
            logger.info("No latest timestamp found. Refreshing for today... {}", LocalDate.now());
            return updateRatings(LocalDate.now());
        }

        logger.info("Refresh Benzinga ratings starting at timestamp {}", latestTimestamp+1);
        // Update starting from timestamp
        BenzingaRatings ratings = benzingaRatingsClient.getRatings(latestTimestamp+1);
        kafkaRatingClient.sendRatings(ratings.getRatings());
        return insertToDb(ratings);
    }

    /**
     * Update 1 days ratings
     *
     * @param localDate date
     * @return number of updated ratings
     */
    public int updateRatings(LocalDate localDate) {
        logger.info("Refresh Benzinga ratings for {}", localDate);
        BenzingaRatings ratings = benzingaRatingsClient.getRatings(localDate, localDate);
        kafkaRatingClient.sendRatings(ratings.getRatings());
        return insertToDb(ratings);
    }

    /**
     * Update days range ratings
     *
     * @param from start date
     * @param to   end date
     * @return number of updated ratings
     */
    public int updateRatings(LocalDate from, LocalDate to) {
        if (to.isBefore(from)) {
            return 0;
        }
        LocalDate current = from;
        int count = 0;
        do {
            count += updateRatings(current);
            current = current.plusDays(1);
        } while (!current.isAfter(to));
        return count;
    }

    private int insertToDb(BenzingaRatings ratings) {
        if (ratings.getRatings().size() != 0) {
            logger.debug("Got {} benzinga ratings. Updating DB ...", ratings.getRatings().size());
            ratingsResource.upsertRatings(ratings.getRatings());
            logger.debug(" ... done", ratings.getRatings().size());
        } else {
            logger.debug("No benzinga ratings to DB upsert");
        }
        return ratings.getRatings().size();
    }

// ECONOMICS

    /**
     * Update Ratings starting from latest timestamp
     *
     * @return number of updated ratings
     */
    public int updateEconomics() {
        logger.debug("Looking for latest timestamp...");
        long latestTimestamp = ratingsResource.getLatestTimestamp();

        // If no latest timestamp found, update last day
        if (latestTimestamp == -1) {
            logger.info("No latest timestamp found. Refreshing for today... {}", LocalDate.now());
            return updateEconomics(LocalDate.now());
        }

        logger.info("Refresh Benzinga ratings starting at timestamp {}", latestTimestamp+1);
        // Update starting from timestamp
        BenzingaEconomics economics = benzingaEconomicsClient.getEconomics(latestTimestamp+1);
        //kafkaRatingClient.sendRatings(economics.getEconomics());
        return insertToDb(economics);
    }

    /**
     * Update 1 days ratings
     *
     * @param localDate date
     * @return number of updated ratings
     */
    public int updateEconomics(LocalDate localDate) {
        logger.info("Refresh Benzinga ratings for {}", localDate);
        BenzingaEconomics economics = benzingaEconomicsClient.getEconomics(localDate, localDate);
        //kafkaRatingClient.sendRatings(ratings.getRatings());
        return insertToDb(economics);
    }

    /**
     * Update days range ratings
     *
     * @param from start date
     * @param to   end date
     * @return number of updated ratings
     */
    public int updateEconomics(LocalDate from, LocalDate to) {
        if (to.isBefore(from)) {
            return 0;
        }
        LocalDate current = from;
        int count = 0;
        do {
            count += updateEconomics(current);
            current = current.plusDays(1);
        } while (!current.isAfter(to));
        return count;
    }

    private int insertToDb(BenzingaEconomics economics) {
        if (economics.getEconomics().size() != 0) {
            logger.debug("Got {} benzinga economics. Updating DB ...", economics.getEconomics().size());
            economicsResource.upsertEconomics(economics.getEconomics());
            logger.debug(" ... done", economics.getEconomics().size());
        } else {
            logger.debug("No benzinga economics to DB upsert");
        }
        return economics.getEconomics().size();
    }


}
