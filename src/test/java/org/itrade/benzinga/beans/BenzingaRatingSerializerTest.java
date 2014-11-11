package org.itrade.benzinga.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.itrade.ProviderConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class BenzingaRatingSerializerTest {
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ProviderConfiguration().objectMapper();
    }

    @Test
    public void should_serialize_ratings_json() throws IOException, URISyntaxException {
        String json = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("ratings-test.json").toURI())));
        BenzingaRatings benzingaRatings = objectMapper.readValue(json, BenzingaRatings.class);
        assertThat(benzingaRatings, notNullValue());
        assertThat(benzingaRatings.getRatings(), notNullValue());
        assertThat(benzingaRatings.getRatings().size(), equalTo(50));
        assertThat(benzingaRatings.getRatings().get(0), notNullValue());
        assertThat(benzingaRatings.getRatings().get(0).getUpdated(), isA(Long.class));
        assertThat(benzingaRatings.getRatings().get(0).getPtCurrent(), isA(Float.class));
        assertThat(benzingaRatings.getRatings().get(0).getPtPrior(), isA(Float.class));
        assertThat(benzingaRatings.getRatings().get(0).getDate().toString(), equalTo("2014-11-07"));
        assertThat(benzingaRatings.getRatings().get(0).getTime(), isA(LocalTime.class));
        assertThat(benzingaRatings.getRatings().get(0).getTime().toString(), equalTo("09:00"));

        String valueAsString = objectMapper.writeValueAsString(benzingaRatings);
        assertThat(valueAsString, notNullValue());
    }
}