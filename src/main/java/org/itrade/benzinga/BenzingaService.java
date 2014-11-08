package org.itrade.benzinga;

import org.itrade.benzinga.beans.BenzingaRatings;
import org.itrade.benzinga.client.BenzingaClient;
import org.itrade.benzinga.resource.RatingsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Created by dimapod on 08/11/14.
 */
@Service
public class BenzingaService {

    @Autowired
    private RatingsResource ratingsResource;

    @Autowired
    private BenzingaClient benzingaClient;

    public void updateRatings(LocalDate localDate) {
        BenzingaRatings ratings = benzingaClient.getRatings(localDate, localDate);
        if (ratings != null && ratings.getRatings() != null && ratings.getRatings().size() != 0) {
            ratingsResource.upsertRatings(ratings.getRatings());
        }
    }

}
