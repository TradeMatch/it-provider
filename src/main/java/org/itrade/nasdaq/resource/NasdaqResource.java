package org.itrade.nasdaq.resource;

import com.google.common.collect.Lists;
import com.mongodb.Mongo;
import org.itrade.nasdaq.beans.NasdaqCompany;
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

@Service
public class NasdaqResource {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Mongo mongo;

    @Value("${mongo.db.name:provider}")
    private String dbName;

    @Value("${mongo.db.collection.nasdaqCompanies:nasdaqCompanies}")
    private String collection;

    @PostConstruct
    private void init() {
//        logger.debug("NasdaqCompanies: ensureIndexes");
//        getCollection().ensureIndex("{symbol: 1}", "{unique: true, background: true}");
    }

    private MongoCollection getCollection() {
        return (new Jongo(mongo.getDB(this.dbName))).getCollection(this.collection);
    }

    public List<NasdaqCompany> upsert(List<NasdaqCompany> nasdaqCompanies) {
        MongoCollection collection = getCollection();
        nasdaqCompanies.forEach(collection::save);
        return nasdaqCompanies;
    }

    public List<NasdaqCompany> getHistoricalData(int offset, int limit) {
        Iterable<NasdaqCompany> as = getCollection().find().skip(offset).limit(limit).as(NasdaqCompany.class);
        return Lists.newArrayList(as);
    }

    public List<NasdaqCompany> getHistoricalDataBySymbol(String ticker, int offset, int limit) {
        Iterable<NasdaqCompany> as = getCollection().find("{symbol: #}", ticker).skip(offset).limit(limit).as(NasdaqCompany.class);
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
