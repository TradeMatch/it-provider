package org.itrade.yahoo.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.itrade.ProviderConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class HistoricalDataSerializerTest {

    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ProviderConfiguration().objectMapper();
    }

    @Test
    public void should_serialize_yahoo_historical_json() throws IOException, URISyntaxException {
        String json = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("historicalData-test.json").toURI())));
        HistoricalData historicalData = objectMapper.readValue(json, HistoricalData.class);
        assertThat(historicalData, notNullValue());
        assertThat(historicalData.getQuery(), notNullValue());
        assertThat(historicalData.getQuery().getCount(), equalTo(6));
        assertThat(historicalData.getQuery().getResults(), notNullValue());
        assertThat(historicalData.getQuery().getResults().getQuote(), notNullValue());
        assertThat(historicalData.getQuery().getResults().getQuote().size(), equalTo(6));
        assertThat(historicalData.getQuery().getResults().getQuote().get(0), notNullValue());
        assertThat(historicalData.getQuery().getResults().getQuote().get(0).getHigh(), equalTo(109.33F));

        String valueAsString = objectMapper.writeValueAsString(historicalData);
        assertThat(valueAsString, notNullValue());
    }

}