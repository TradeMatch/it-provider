package org.itrade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;

@Configuration
public class ProviderConfiguration {

    @Value("${mongo.configuration.host:localhost}")
    private String mongoHost;

    @Value("${mongo.configuration.port:27017}")
    private int mongoPort;

    @Bean
    public Mongo mongoDb() throws UnknownHostException {
        return new MongoClient(mongoHost, mongoPort);
    }

    @Bean
    public RestTemplate restTemplate() throws UnknownHostException {
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }

}
