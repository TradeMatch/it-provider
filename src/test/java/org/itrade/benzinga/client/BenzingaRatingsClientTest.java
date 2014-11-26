package org.itrade.benzinga.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.itrade.benzinga.beans.BenzingaRatings;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class BenzingaRatingsClientTest {
    private BenzingaRatingsClient benzingaRatingsClient;

    @Before
    public void setUp() throws Exception {
        benzingaRatingsClient = new BenzingaRatingsClient();
        benzingaRatingsClient.setRestTemplate(new RestTemplate());
        TokenResolver tokenResolver = new TokenResolver();
        tokenResolver.setFile(new File(System.getProperty("user.home") + "/benzinga.token"));
        benzingaRatingsClient.setTokenResolver(tokenResolver);
        benzingaRatingsClient.init();
        benzingaRatingsClient.setObjectMapper(new ObjectMapper());
    }

    @Test
    public void should_call_benzinga_ratings_api() {
        BenzingaRatings ratings = benzingaRatingsClient.getRatings(LocalDate.of(2014, 11, 7), LocalDate.of(2014, 11, 7));

        assertThat(ratings.getRatings(), notNullValue());
        assertThat(ratings.getRatings().size(), equalTo(50));

        System.out.println(ratings);
    }
}