package org.itrade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;
import java.util.Arrays;

@Configuration
public class ProviderConfiguration {

    @Value("${mongo.configuration.host:localhost}")
    private String mongoHost;

    @Value("${mongo.configuration.port:27017}")
    private int mongoPort;

    // Auth
    @Value("${mongo.db.name:provider}")
    private String dbName;

    @Value("${mongo.configuration.user:it-provider}")
    private String mongoUser;

    @Value("${mongo.configuration.password:}")
    private String mongoPassword;

    @Bean
    public Mongo mongoDb() throws UnknownHostException {
        // Auth
        MongoCredential credential = MongoCredential.createMongoCRCredential(mongoUser, dbName, mongoPassword.toCharArray());
        MongoClient mongoAuthClient = new MongoClient(new ServerAddress(mongoHost, mongoPort), Arrays.asList(credential));
        // Use auth client or simple client if no mongoUser is specified (tests)
        return mongoUser != null ? mongoAuthClient : new MongoClient(mongoHost, mongoPort);
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
