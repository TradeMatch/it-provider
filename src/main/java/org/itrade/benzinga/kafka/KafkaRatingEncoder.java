package org.itrade.benzinga.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;
import org.itrade.ProviderConfiguration;
import org.itrade.benzinga.BenzingaException;
import org.itrade.benzinga.beans.BenzingaRating;

/**
 * Created by Dmytro Podyachiy on 23/11/14.
 */
public class KafkaRatingEncoder implements Encoder<BenzingaRating> {

    private final ObjectMapper objectMapper;

    public KafkaRatingEncoder(VerifiableProperties verifiableProperties)  {
        objectMapper = new ProviderConfiguration().objectMapper();
    }

    @Override
    public byte[] toBytes(BenzingaRating benzingaRating) {
        try {
            return objectMapper.writeValueAsBytes(benzingaRating);
        } catch (JsonProcessingException e) {
            throw new BenzingaException("Cannot serialize", e);
        }
    }
}
