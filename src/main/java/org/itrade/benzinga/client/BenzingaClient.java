package org.itrade.benzinga.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.itrade.benzinga.BenzingaException;
import org.itrade.benzinga.beans.BenzingaRatings;
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
    private static final String benzingaCalendarUrl = "http://api.benzinga.com/api/v2/calendar/{method}";
    private Integer pageSize = null;

    @Autowired
    private TokenResolver tokenResolver;
    private ObjectMapper mapper;

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
                .queryParam("token", token)
                .build();
        prepareHeaders();
        mapper = new ObjectMapper();
    }

    private void prepareHeaders() {
        headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
    }

    public BenzingaRatings getRatings(LocalDate from, LocalDate to) {
        HashMap<String, String> params = new HashMap<>();
        params.put("method", CalendarMethods.ratings);
        params.put("date_from", from.format(DateTimeFormatter.ISO_DATE));
        params.put("date_to", to.format(DateTimeFormatter.ISO_DATE));

        URI uri = builder.expand(params).toUri();
        HttpEntity<String> response = restTemplate.exchange(
                uri, HttpMethod.GET, new HttpEntity<>(headers), String.class);

        return deserialize(response.getBody());
    }

    private BenzingaRatings deserialize(String json) {
        if (json == null || json.equals("[]")) {
            return new BenzingaRatings();
        }

        try {
            return mapper.readValue(json, BenzingaRatings.class);
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
