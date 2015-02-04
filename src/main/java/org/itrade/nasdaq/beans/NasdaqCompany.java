package org.itrade.nasdaq.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.Id;

import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NasdaqCompany {

    private String symbol;
    private String name;
    private Double lastSale;
    private Double marketCap;
    private Double adrTso;
    private Integer ipoYear;
    private String sector;
    private String industry;
    private String summaryQuote;

    private Date updated;

    public NasdaqCompany() {
    }

    public NasdaqCompany(String symbol) {
        this.symbol = symbol;
    }

    @Id
    @JsonProperty("_id")
    public String getId() {
        return getSymbol();
    }

    public String getSymbol() {
        return symbol;
    }

    public NasdaqCompany setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public String getName() {
        return name;
    }

    public NasdaqCompany setName(String name) {
        this.name = name;
        return this;
    }

    public Double getLastSale() {
        return lastSale;
    }

    public NasdaqCompany setLastSale(Double lastSale) {
        this.lastSale = lastSale;
        return this;
    }

    public Double getMarketCap() {
        return marketCap;
    }

    public NasdaqCompany setMarketCap(Double marketCap) {
        this.marketCap = marketCap;
        return this;
    }

    public Double getAdrTso() {
        return adrTso;
    }

    public NasdaqCompany setAdrTso(Double adrTso) {
        this.adrTso = adrTso;
        return this;
    }

    public Integer getIpoYear() {
        return ipoYear;
    }

    public NasdaqCompany setIpoYear(Integer ipoYear) {
        this.ipoYear = ipoYear;
        return this;
    }

    public String getSector() {
        return sector;
    }

    public NasdaqCompany setSector(String sector) {
        this.sector = sector;
        return this;
    }

    public String getIndustry() {
        return industry;
    }

    public NasdaqCompany setIndustry(String industry) {
        this.industry = industry;
        return this;
    }

    public String getSummaryQuote() {
        return summaryQuote;
    }

    public NasdaqCompany setSummaryQuote(String summaryQuote) {
        this.summaryQuote = summaryQuote;
        return this;
    }

    public Date getUpdated() {
        return updated;
    }

    public NasdaqCompany setUpdated(Date updated) {
        this.updated = updated;
        return this;
    }

    @Override
    public String toString() {
        return "NasdaqCompany{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", lastSale=" + lastSale +
                ", marketCap=" + marketCap +
                ", adrTso=" + adrTso +
                ", ipoYear=" + ipoYear +
                ", sector='" + sector + '\'' +
                ", industry='" + industry + '\'' +
                ", summaryQuote='" + summaryQuote + '\'' +
                ", updated=" + updated +
                '}';
    }
}
