package org.itrade.nasdaq.client;

import org.junit.Before;
import org.junit.Test;

public class NasdaqClientTest {
    private NasdaqClient nasdaqClient;

    @Before
    public void setUp() throws Exception {
        nasdaqClient = new NasdaqClient();
    }

    @Test
    public void should_get_nasdaq() {
        String nasdaqCompanies = nasdaqClient.getNasdaqCompanies();

        System.out.println(nasdaqCompanies);
    }
}