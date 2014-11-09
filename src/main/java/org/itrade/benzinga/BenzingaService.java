package org.itrade.benzinga;

import org.itrade.benzinga.beans.BenzingaRatings;
import org.itrade.benzinga.client.BenzingaClient;
import org.itrade.benzinga.resource.RatingsResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Created by Dmytro Podyachiy on 08/11/14.
 */
@Service
public class BenzingaService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RatingsResource ratingsResource;

    @Autowired
    private BenzingaClient benzingaClient;

    public int updateRatings(LocalDate localDate) {
        logger.info("Refresh Benzinga ratings for {}", localDate);
        BenzingaRatings ratings = benzingaClient.getRatings(localDate, localDate);
        if (ratings.getRatings().size() != 0) {
            logger.debug("Got {} benzinga ratings. Updating DB ...", ratings.getRatings().size());
            ratingsResource.upsertRatings(ratings.getRatings());
            logger.debug(" ... done", ratings.getRatings().size());
        } else {
            logger.debug("No benzinga ratings found");
        }
        return ratings.getRatings().size();
    }

    public int updateRatings(LocalDate from, LocalDate to) {
        if (to.isBefore(from)) {
            return 0;
        }

        LocalDate current = from;

        do {
            current = current.plusDays(1);

            updateRatings(current);

            System.out.println(current);
        } while (current.isBefore(to));


        return 0;
    }

}
