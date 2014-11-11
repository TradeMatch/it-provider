package org.itrade.benzinga.resource;

import com.google.common.collect.Lists;
import com.mongodb.Mongo;
import org.itrade.benzinga.beans.BenzingaRating;
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
public class RatingsResource {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Mongo mongo;

    @Value("${mongo.db.name:provider}")
    private String dbName;

    @Value("${mongo.db.collection.ratings:ratings}")
    private String collection;

    @PostConstruct
    private void init() {
        logger.debug("RatingCollection: ensureIndexes");
        getRatingCollection().ensureIndex("{updated: 1}", "{background: true}");
    }

    private MongoCollection getRatingCollection() {
        return (new Jongo(mongo.getDB(this.dbName))).getCollection(this.collection);
    }

    public List<BenzingaRating> upsertRatings(List<BenzingaRating> benzingaRatings) {
        MongoCollection ratingCollection = getRatingCollection();
        benzingaRatings.forEach(ratingCollection::save);
        return benzingaRatings;
    }

    public List<BenzingaRating> getRatings(int offset, int limit) {
        Iterable<BenzingaRating> as = getRatingCollection().find().skip(offset).limit(limit).as(BenzingaRating.class);
        return Lists.newArrayList(as);
    }

    public List<BenzingaRating> getRatingsByTicker(String ticker, int offset, int limit) {
        Iterable<BenzingaRating> as = getRatingCollection().find("{ticker: #}", ticker).skip(offset).limit(limit).as(BenzingaRating.class);
        return Lists.newArrayList(as);
    }

    public long getLatestTimestamp() {
        Iterable<BenzingaRating> as = getRatingCollection().find("{}")
                .sort("{updated: -1}").limit(1).as(BenzingaRating.class);
        List<BenzingaRating> benzingaRatings = Lists.newArrayList(as);

        if (benzingaRatings.size() == 0) {
            return -1;
        }
        return benzingaRatings.get(0).getUpdated();
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
