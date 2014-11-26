package org.itrade.benzinga.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.itrade.benzinga.beans.BenzingaEconomics;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class BenzingaEconomicsClientTest {
    private BenzingaEconomicsClient benzingaEconomicsClient;

    @Before
    public void setUp() throws Exception {
        benzingaEconomicsClient = new BenzingaEconomicsClient();
        benzingaEconomicsClient.setRestTemplate(new RestTemplate());
        TokenResolver tokenResolver = new TokenResolver();
        tokenResolver.setFile(new File(System.getProperty("user.home") + "/benzinga.token"));
        benzingaEconomicsClient.setTokenResolver(tokenResolver);
        benzingaEconomicsClient.init();
        benzingaEconomicsClient.setObjectMapper(new ObjectMapper());
    }

    @Test
    public void should_call_benzinga_economics_api() {
        BenzingaEconomics ratings = benzingaEconomicsClient.getEconomics(LocalDate.of(2014, 11, 7), LocalDate.of(2014, 11, 7));

        assertThat(ratings.getEconomics(), notNullValue());
//        assertThat(ratings.getEconomics().size(), equalTo(50));

        System.out.println(ratings);
    }
}