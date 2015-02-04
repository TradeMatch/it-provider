package org.itrade.nasdaq.resource;

import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import org.itrade.nasdaq.beans.NasdaqCompany;
import org.junit.Before;
import org.junit.Test;

public class NasdaqResourceTest {

    private MongoClient mongoClient;
    private NasdaqResource nasdaqResource;

    @Before
    public void setUp() throws Exception {
        mongoClient = new MongoClient("localhost", 27017);
        nasdaqResource = new NasdaqResource();
        nasdaqResource.setMongo(mongoClient);
        nasdaqResource.setDbName("provider");
        nasdaqResource.setCollection("nasdaqCompanies_Test");
    }

    @Test
    public void should_return_latest_timestamp() {
        nasdaqResource.upsert(Lists.newArrayList(new NasdaqCompany().setSymbol("TEST")));
    }

}