package org.itrade;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;
import java.util.Arrays;

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

}
