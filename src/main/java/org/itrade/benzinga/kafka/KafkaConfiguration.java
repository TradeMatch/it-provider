package org.itrade.benzinga.kafka;

import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;
import org.itrade.benzinga.kafka.KafkaRatingProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfiguration {
    @Value("${kafka.configuration.metadata.broker.list:localhost:9092,localhost:9093}")
    private String brokerList;

    @Value("${kafka.configuration.request.required.acks:1}")
    private String requiredAcks;

    @Bean
    public KafkaRatingProducer kafkaRatingProducer() {
        Properties props = new Properties();
        props.put("metadata.broker.list", brokerList);
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        props.put("serializer.class", "org.itrade.benzinga.kafka.KafkaRatingEncoder");
        props.put("partitioner.class", "org.itrade.benzinga.kafka.KafkaRatingPartitioner");
        props.put("request.required.acks", requiredAcks);
        ProducerConfig config = new ProducerConfig(props);

        return new KafkaRatingProducer(config);
    }

}
