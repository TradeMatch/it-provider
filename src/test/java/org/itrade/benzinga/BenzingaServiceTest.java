package org.itrade.benzinga;

import org.itrade.ProviderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProviderApplication.class)
public class BenzingaServiceTest {

    @Autowired
    private BenzingaService benzingaService;

    @Test
    public void should_update_today_ratings() {
        benzingaService.updateRatings(LocalDate.of(2014, 11, 5));
    }

    @Test
    public void should_update_range_ratings() {
        benzingaService.updateRatings(LocalDate.of(2014, 11, 3), LocalDate.of(2014, 11, 3));
    }

    @Test
    public void should_update_range_economics() {
        benzingaService.updateEconomics(LocalDate.of(2014, 11, 3), LocalDate.of(2014, 11, 3));
    }

    @Test
    public void should_update_latest() {
        benzingaService.updateRatings();
    }
}