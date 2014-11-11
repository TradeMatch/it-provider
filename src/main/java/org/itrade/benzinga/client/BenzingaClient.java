package org.itrade.benzinga.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.itrade.benzinga.BenzingaException;
import org.itrade.benzinga.beans.BenzingaRatings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Service
public class BenzingaClient {
    Logger logger = LoggerFactory.getLogger(getClass());

    private static final String benzingaCalendarUrl = "http://api.benzinga.com/api/v2/calendar/{method}";
    private int pageSize = 50;

    @Autowired
    private TokenResolver tokenResolver;

    @Autowired
    private ObjectMapper objectMapper;

    interface CalendarMethods {
        String ratings = "ratings";
    }

    @Autowired
    private RestTemplate restTemplate;
    private UriComponents builder;
    private HttpHeaders headers;

    @PostConstruct
    public void init() {
        String token = tokenResolver.resolveToken();
        builder = UriComponentsBuilder.fromHttpUrl(benzingaCalendarUrl)
                .queryParam("parameters[date_from]", "{date_from}")
                .queryParam("parameters[date_to]", "{date_to}")
                .queryParam("parameters[updated]", "{updated}")
                .queryParam("token", token)
                .queryParam("pagesize", pageSize)
                .queryParam("page", "{page}")
                .build();
        prepareHeaders();
    }

    private void prepareHeaders() {
        headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
    }

    public BenzingaRatings getRatings(long startTimestamp) {
        BenzingaRatings resultRatings = new BenzingaRatings();

        int page = 0;
        BenzingaRatings ratings;
        do {
            String response = getRatingsInternal(null, null, startTimestamp, page++);
            ratings = deserialize(response);
            logger.debug(" page {}: {} ratings", page, ratings.getRatings().size());
            resultRatings.getRatings().addAll(ratings.getRatings());
        } while (ratings.getRatings().size() == pageSize && page < 10);

        return resultRatings;
    }

    public BenzingaRatings getRatings(LocalDate from, LocalDate to) {
        BenzingaRatings resultRatings = new BenzingaRatings();

        int page = 0;
        BenzingaRatings ratings;
        do {
            String response = getRatingsInternal(from, to, -1, page++);
            ratings = deserialize(response);
            logger.debug(" page {}: {} ratings", page, ratings.getRatings().size());
            resultRatings.getRatings().addAll(ratings.getRatings());
        } while (ratings.getRatings().size() == pageSize && page < 10);

        return resultRatings;
    }

    protected String getRatingsInternal(LocalDate from, LocalDate to, long startTimestamp, int page) {
        HashMap<String, String> params = new HashMap<>();
        params.put("method", CalendarMethods.ratings);
        params.put("date_from", from != null ? from.format(DateTimeFormatter.ISO_DATE) : "");
        params.put("date_to", to != null ? to.format(DateTimeFormatter.ISO_DATE): "");
        params.put("page", String.valueOf(page));
        params.put("updated", startTimestamp != -1 ? String.valueOf(startTimestamp) : "");

        URI uri = builder.expand(params).toUri();
        HttpEntity<String> response = restTemplate.exchange(
                uri, HttpMethod.GET, new HttpEntity<>(headers), String.class);

        return response.getBody();
    }

    private BenzingaRatings deserialize(String json) {
        if (json == null || json.equals("[]")) {
            BenzingaRatings benzingaRatings = new BenzingaRatings();
            benzingaRatings.setRatings(Lists.newArrayList());
            return benzingaRatings;
        }

        try {
            return objectMapper.readValue(json, BenzingaRatings.class);
        } catch (IOException e) {
            throw new BenzingaException("Benzinga response cannot be converted to object", e);
        }
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setTokenResolver(TokenResolver tokenResolver) {
        this.tokenResolver = tokenResolver;
    }
}
