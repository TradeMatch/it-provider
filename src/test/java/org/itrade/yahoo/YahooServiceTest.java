package org.itrade.yahoo;

import org.itrade.ProviderApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProviderApplication.class)
public class YahooServiceTest {

    @Autowired
    private YahooService yahooService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void should_update_historical_data_aapl() throws Exception {

        yahooService.update();

    }
}