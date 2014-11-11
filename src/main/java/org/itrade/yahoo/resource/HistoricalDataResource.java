package org.itrade.yahoo.resource;

import com.google.common.collect.Lists;
import com.mongodb.Mongo;
import org.itrade.yahoo.beans.Quote;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by dimapod on 08/11/14.
 */
@Service
public class HistoricalDataResource {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Mongo mongo;

    @Value("${mongo.db.name:provider}")
    private String dbName;

    @Value("${mongo.db.collection.historicalData:historicalData}")
    private String collection;

    @PostConstruct
    private void init() {
        logger.debug("HistoricalData: ensureIndexes");
        getCollection().ensureIndex("{Date: 1, Symbol: 1}", "{unique: true, background: true}");
    }

    private MongoCollection getCollection() {
        return (new Jongo(mongo.getDB(this.dbName))).getCollection(this.collection);
    }

    public List<Quote> upsert(List<Quote> quotes) {
        MongoCollection collection = getCollection();
        quotes.forEach(collection::save);
        return quotes;
    }

    public List<Quote> getHistoricalData(int offset, int limit) {
        Iterable<Quote> as = getCollection().find().skip(offset).limit(limit).as(Quote.class);
        return Lists.newArrayList(as);
    }

    public List<Quote> getHistoricalDataByTicker(String ticker, int offset, int limit) {
        Iterable<Quote> as = getCollection().find("{ticker: #}", ticker).skip(offset).limit(limit).as(Quote.class);
        return Lists.newArrayList(as);
    }

    public void setMongo(Mongo mongo) {
        this.mongo = mongo;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }
}
