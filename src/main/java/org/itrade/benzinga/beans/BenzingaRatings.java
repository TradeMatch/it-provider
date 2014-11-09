package org.itrade.benzinga.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/*

{
    "ratings": [
        {
            "id": "15765",
            ...
        }
    ]
}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BenzingaRatings {

    public List<BenzingaRating> ratings;

    public BenzingaRatings() {
        ratings = new ArrayList<>();
    }

    public List<BenzingaRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<BenzingaRating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "BenzingaRatings{" +
                "ratings=" + ratings +
                '}';
    }
}
