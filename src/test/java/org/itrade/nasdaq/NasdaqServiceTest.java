package org.itrade.nasdaq;

import org.itrade.nasdaq.beans.NasdaqCompany;
import org.junit.Before;
import org.junit.Test;

public class NasdaqServiceTest {

    private NasdaqService nasdaqService;

    @Before
    public void setUp() throws Exception {
        nasdaqService = new NasdaqService();
    }

    @Test
    public void should_parse_nasdaqCompany() {
        NasdaqCompany nasdaqCompany = nasdaqService.parseLine("\"TFSC\",\"1347 Capital Corp.\",\"9.53\",\"56684440\",\"n/a\",\"2014\",\"Finance\",\"Business Services\",\"http://www.nasdaq.com/symbol/tfsc\"");
        System.out.println(nasdaqCompany);
    }

}