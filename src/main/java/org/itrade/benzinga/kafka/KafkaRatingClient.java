package org.itrade.benzinga.kafka;

import kafka.producer.KeyedMessage;
import org.itrade.benzinga.beans.BenzingaRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dmytro Podyachiy on 23/11/14.
 */
@Service
public class KafkaRatingClient {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private KafkaRatingProducer kafkaRatingProducer;

    @Value("${kafka.rating.topic:ratings}")
    private String ratingTopic;

    public void sendRatings(List<BenzingaRating> ratings) {
        logger.info("Send to kafka: {}", ratings.size());
        try {
/*
            ratings.forEach((rating) ->
                    kafkaRatingProducer.send(new KeyedMessage<>(ratingTopic, rating.getTicker(), rating)));
*/
        } catch (Exception e) {
            logger.error("Cannot send messages to kafka", e);
        }
    }


}
