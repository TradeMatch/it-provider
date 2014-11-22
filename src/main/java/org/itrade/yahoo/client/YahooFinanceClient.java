package org.itrade.yahoo.client;

import org.itrade.yahoo.YahooException;
import org.itrade.yahoo.beans.HistoricalData;
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
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dmytro Podyachiy on 11/11/14.
 */
@Service
public class YahooFinanceClient {
    Logger logger = LoggerFactory.getLogger(getClass());

    private static final String baseUrl = "http://query.yahooapis.com/v1/public/yql";
    private static final String query = "select * from yahoo.finance.historicaldata where symbol in ({symbols}) and startDate=\"{date_from}\" and endDate=\"{date_to}\"";
    private UriComponents builder;

    @Autowired
    private RestTemplate restTemplate;
    private HttpHeaders headers;

    @PostConstruct
    public void init() {
        builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("q", "{q}")
                .queryParam("format", "json")
                .queryParam("env", "store://datatables.org/alltableswithkeys")
                .build();
        prepareHeaders();
    }

    private void prepareHeaders() {
        headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
    }

    protected URI prepareUri(List<String> symbols, LocalDate from, LocalDate to) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl);
        uriComponentsBuilder.queryParam("format", "json");

        String concatSymbols = symbols.stream()
                .map(a -> "\"" + a + "\"")
                .collect(Collectors.joining(","));
        String q = query.replace("{symbols}", concatSymbols)
                .replace("{date_from}", from.toString())
                .replace("{date_to}", to.toString());

        HashMap<String, String> params = new HashMap<>();
        params.put("q", q);
        try {
            uriComponentsBuilder.queryParam("q", URLEncoder.encode(q, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new YahooException("Impossible to encode query", e);
        }
        return builder.expand(params).toUri();
    }

    public HistoricalData getHistoricalData(List<String> symbols, LocalDate from, LocalDate to) {
        URI uri = prepareUri(symbols, from, to);

        logger.debug("Call '{}'", uri.toString());
        HttpEntity<HistoricalData> response = restTemplate.exchange(
                uri, HttpMethod.GET, new HttpEntity<>(headers), HistoricalData.class);

        return response.getBody();
    }


    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
