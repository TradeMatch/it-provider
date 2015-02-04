package org.itrade.nasdaq;

import org.itrade.nasdaq.beans.NasdaqCompany;
import org.itrade.nasdaq.client.NasdaqClient;
import org.itrade.nasdaq.resource.NasdaqResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NasdaqService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private NasdaqClient nasdaqClient;

    @Autowired
    private NasdaqResource nasdaqResource;

    public int update() {
        logger.info("Get companies from nasdaq.com...");
        String nasdaqCompanies = nasdaqClient.getNasdaqCompanies();
        List<NasdaqCompany> companyList = parseCSV(nasdaqCompanies);

        logger.debug("  inserting {} nasdaq companies to DB", companyList.size());
        nasdaqResource.upsert(companyList);

        logger.info("Nasdaq companies updated: {}", companyList.size());
        return companyList.size();
    }

    protected List<NasdaqCompany> parseCSV(String companiesStr) {
        String companies[] = companiesStr.split("\\r?\\n");
        return Arrays.stream(companies).skip(1).map(this::parseLine).collect(Collectors.toList());
    }

    protected NasdaqCompany parseLine(String line) {
        List<String> splitCompany = Arrays.stream(line.split(","))
                .map(s -> s.substring(1, s.length() - 1)).collect(Collectors.toList());

        return new NasdaqCompany(splitCompany.get(0))
                .setName(splitCompany.get(1))
                .setLastSale(parseDouble(splitCompany.get(2)))
                .setMarketCap(parseDouble(splitCompany.get(3)))
                .setAdrTso(parseDouble(splitCompany.get(4)))
                .setIpoYear(parseInteger(splitCompany.get(5)))
                .setSector(splitCompany.get(6))
                .setIndustry(splitCompany.get(7))
                .setSummaryQuote(splitCompany.get(8))
                .setUpdated(new Date());
    }

    private Double parseDouble(String strVal) {
        try {
            return Double.parseDouble(strVal);
        } catch (Exception e) {
            return null;
        }
    }

    private Integer parseInteger(String strVal) {
        try {
            return Integer.parseInt(strVal);
        } catch (Exception e) {
            return null;
        }
    }

}
