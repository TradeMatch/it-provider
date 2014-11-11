package org.itrade.benzinga.resource;

import com.mongodb.MongoClient;
import org.junit.Before;
import org.junit.Test;

public class RatingsResourceTest {

    private MongoClient mongoClient;
    private RatingsResource ratingsResource;

    @Before
    public void setUp() throws Exception {
        mongoClient = new MongoClient("localhost", 27017);
        ratingsResource = new RatingsResource();
        ratingsResource.setMongo(mongoClient);
        ratingsResource.setDbName("provider");
        ratingsResource.setCollection("ratings");
    }

    @Test
    public void should_return_latest_timestamp() {
        long latestTimestamp = ratingsResource.getLatestTimestamp();
    }
}