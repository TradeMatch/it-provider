package org.itrade.yahoo.client;

import com.google.common.collect.Lists;
import org.hamcrest.MatcherAssert;
import org.itrade.yahoo.beans.HistoricalData;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class YahooFinanceClientTest {

    private YahooFinanceClient yahooFinanceClient;
    private MockRestServiceServer mockServer;

    @Before
    public void setUp() throws Exception {
        yahooFinanceClient = new YahooFinanceClient();
        yahooFinanceClient.init();

        RestTemplate restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(restTemplate);
        yahooFinanceClient.setRestTemplate(restTemplate);

        String json = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("historicalData-test.json").toURI())));

        mockServer.expect(requestTo(any(String.class))).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

    }

    @Test
    public void should_prepare_uri_for_aapl() {
        URI uri = yahooFinanceClient.prepareUri(Lists.newArrayList("AAPL"), LocalDate.of(2014, 9, 1), LocalDate.of(2014,10,1));
        assertThat(uri.toString(), equalTo("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20in%20(%22AAPL%22)%20and%20startDate=%222014-09-01%22%20and%20endDate=%222014-10-01%22&format=json&env=store://datatables.org/alltableswithkeys"));
    }

    @Test
    public void should_prepare_uri_for_aapl_and_aaon() {
        URI uri = yahooFinanceClient.prepareUri(Lists.newArrayList("AAPL", "AAON"), LocalDate.of(2014, 9, 1), LocalDate.of(2014, 10, 1));
        assertThat(uri.toString(), equalTo("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20in%20(%22AAPL%22,%22AAON%22)%20and%20startDate=%222014-09-01%22%20and%20endDate=%222014-10-01%22&format=json&env=store://datatables.org/alltableswithkeys"));
    }

    @Test
    public void should_call_yahoo_historical_data_api() {
        HistoricalData historicalData = yahooFinanceClient.getHistoricalData(Lists.newArrayList("AAPL", "AAON"), LocalDate.of(2014,9,1), LocalDate.of(2014,10,1));
        MatcherAssert.assertThat(historicalData, notNullValue());
        MatcherAssert.assertThat(historicalData.getQuery(), notNullValue());
        MatcherAssert.assertThat(historicalData.getQuery().getCount(), equalTo(6));
        MatcherAssert.assertThat(historicalData.getQuery().getResults(), notNullValue());
        MatcherAssert.assertThat(historicalData.getQuery().getResults().getQuote(), notNullValue());
        MatcherAssert.assertThat(historicalData.getQuery().getResults().getQuote().size(), equalTo(6));

        mockServer.verify();
    }

}