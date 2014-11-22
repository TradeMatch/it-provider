package org.itrade.yahoo;

import org.itrade.ProviderApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProviderApplication.class)
public class YahooServiceTest {

    public static final int SIZE = 500;
    @Autowired
    private YahooService yahooService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void should_update_today_historical_data() throws Exception {
        yahooService.update();
    }

    @Test
    public void should_compute_partition_size() {
        int size = yahooService.computePartitionSize(LocalDate.of(2014, 10, 10), LocalDate.of(2014, 10, 10));
        assertThat(size, equalTo(SIZE));
        size = yahooService.computePartitionSize(LocalDate.of(2014, 10, 10), LocalDate.of(2014, 10, 11));
        assertThat(size, equalTo(SIZE /2));
        size = yahooService.computePartitionSize(LocalDate.of(2014, 10, 10), LocalDate.of(2014, 10, 12));
        assertThat(size, equalTo(SIZE /3));
        size = yahooService.computePartitionSize(LocalDate.of(2014, 9, 30), LocalDate.of(2014, 10, 3));
        assertThat(size, equalTo(SIZE /4));
    }
}