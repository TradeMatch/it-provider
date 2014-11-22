package org.itrade.tickers.resource;

import com.google.common.collect.Lists;
import com.mongodb.Mongo;
import org.itrade.tickers.beans.Ticker;
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
public class TickersResource {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Mongo mongo;

    @Value("${mongo.db.name:provider}")
    private String dbName;

    @Value("${mongo.db.collection.nasdaq:nasdaq}")
    private String collectionNasdaq;

    @PostConstruct
    private void init() {
        //logger.info("RatingCollection: ensureIndexes");
        //getCollection().ensureIndex("{Symbol: 1}", "{background: true}");
    }

    private MongoCollection getNasdaqCollection() {
        return (new Jongo(mongo.getDB(this.dbName))).getCollection(this.collectionNasdaq);
    }

    public List<Ticker> getNasdaqTickers(int offset, int limit) {
        Iterable<Ticker> as = getNasdaqCollection().find().skip(offset).limit(limit).as(Ticker.class);
        return Lists.newArrayList(as);
    }

    public void setMongo(Mongo mongo) {
        this.mongo = mongo;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setNasdaqCollection(String collection) {
        this.collectionNasdaq = collection;
    }
}
