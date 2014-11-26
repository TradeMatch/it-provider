package org.itrade.benzinga.kafka;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class KafkaRatingPartitioner implements Partitioner {
    public KafkaRatingPartitioner(VerifiableProperties props) {
    }

    public int partition(Object key, int numPartitions) {
        // TODO
        return 0;
    }

}
