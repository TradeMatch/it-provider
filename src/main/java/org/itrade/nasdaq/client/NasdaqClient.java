package org.itrade.nasdaq.client;

import org.apache.commons.io.IOUtils;
import org.itrade.nasdaq.NasdaqException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

@Service
public class NasdaqClient {
    Logger logger = LoggerFactory.getLogger(getClass());

    public static final String NASDAQ_URL = "http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=NASDAQ&render=download";

    public String getNasdaqCompanies() {
        logger.debug("  call '{}'", NASDAQ_URL);

        try (BufferedInputStream in =
                     new BufferedInputStream(new URL(NASDAQ_URL).openStream())) {
            return IOUtils.toString(in, "UTF-8");
        } catch (IOException e) {
            throw new NasdaqException("Impossible to read from " + NASDAQ_URL, e);
        }
    }

}
