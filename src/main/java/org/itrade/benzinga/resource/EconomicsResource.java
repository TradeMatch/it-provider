package org.itrade.benzinga.resource;

import com.google.common.collect.Lists;
import com.mongodb.Mongo;
import org.itrade.benzinga.beans.Economic;
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
public class EconomicsResource {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Mongo mongo;

    @Value("${mongo.db.name:provider}")
    private String dbName;

    @Value("${mongo.db.collection.economics:economics}")
    private String collection;

    @PostConstruct
    private void init() {
        logger.debug("EconomicCollection: ensureIndexes");
        getEconomicCollection().ensureIndex("{updated: 1}", "{background: true}");
    }

    private MongoCollection getEconomicCollection() {
        return (new Jongo(mongo.getDB(this.dbName))).getCollection(this.collection);
    }

    public List<Economic> upsertEconomics(List<Economic> economics) {
        MongoCollection economicCollection = getEconomicCollection();
        economics.forEach(economicCollection::save);
        return economics;
    }

    public List<Economic> getEconomics(int offset, int limit) {
        Iterable<Economic> as = getEconomicCollection().find().skip(offset).limit(limit).as(Economic.class);
        return Lists.newArrayList(as);
    }

    public List<Economic> getEconomicsByTicker(String ticker, int offset, int limit) {
        Iterable<Economic> as = getEconomicCollection().find("{ticker: #}", ticker).skip(offset).limit(limit).as(Economic.class);
        return Lists.newArrayList(as);
    }

    public long getLatestTimestamp() {
        Iterable<Economic> as = getEconomicCollection().find("{}")
                .sort("{updated: -1}").limit(1).as(Economic.class);
        List<Economic> economics = Lists.newArrayList(as);

        if (economics.size() == 0) {
            return -1;
        }
        return economics.get(0).getUpdated();
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
