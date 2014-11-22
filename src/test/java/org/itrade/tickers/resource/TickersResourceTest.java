package org.itrade.tickers.resource;

import com.mongodb.MongoClient;
import org.itrade.tickers.beans.Ticker;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TickersResourceTest {


    private MongoClient mongoClient;
    private TickersResource tickersResource;

    @Before
    public void setUp() throws Exception {
        mongoClient = new MongoClient("localhost", 27017);
        tickersResource = new TickersResource();
        tickersResource.setMongo(mongoClient);
        tickersResource.setDbName("provider");
        tickersResource.setNasdaqCollection("nasdaq");
    }

    @Test
    public void should_return_latest_timestamp() {
        List<Ticker> nasdaqTickers = tickersResource.getNasdaqTickers(0, 0);

    }

}