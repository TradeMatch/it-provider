
package org.itrade.tickers.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "Symbol",
        "Name",
        "LastSale",
        "MarketCap",
        "ADR TSO",
        "IPOyear",
        "Sector",
        "industry",
        "Summary Quote"
})
public class Ticker {

    @Id
    @ObjectId
    @JsonProperty("id")
    private String id;
    @JsonProperty("Symbol")
    private String Symbol;
    @JsonProperty("Name")
    private String Name;
    @JsonProperty("LastSale")
    private String LastSale;
    @JsonProperty("MarketCap")
    private String MarketCap;
    @JsonProperty("ADR TSO")
    private String ADRTSO;
    @JsonProperty("IPOyear")
    private String IPOyear;
    @JsonProperty("Sector")
    private String Sector;
    @JsonProperty("industry")
    private String industry;
    @JsonProperty("Summary Quote")
    private String SummaryQuote;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastSale() {
        return LastSale;
    }

    public void setLastSale(String lastSale) {
        LastSale = lastSale;
    }

    public String getMarketCap() {
        return MarketCap;
    }

    public void setMarketCap(String marketCap) {
        MarketCap = marketCap;
    }

    public String getADRTSO() {
        return ADRTSO;
    }

    public void setADRTSO(String ADRTSO) {
        this.ADRTSO = ADRTSO;
    }

    public String getIPOyear() {
        return IPOyear;
    }

    public void setIPOyear(String IPOyear) {
        this.IPOyear = IPOyear;
    }

    public String getSector() {
        return Sector;
    }

    public void setSector(String sector) {
        Sector = sector;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSummaryQuote() {
        return SummaryQuote;
    }

    public void setSummaryQuote(String summaryQuote) {
        SummaryQuote = summaryQuote;
    }
}
