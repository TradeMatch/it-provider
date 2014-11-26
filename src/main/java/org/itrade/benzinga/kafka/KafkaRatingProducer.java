package org.itrade.benzinga.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;
import org.itrade.benzinga.beans.BenzingaRating;

/**
 * Created by Dmytro Podyachiy on 23/11/14.
 */
public class KafkaRatingProducer extends Producer<String, BenzingaRating> {
    public KafkaRatingProducer(ProducerConfig config) {
        super(config);
    }
}
