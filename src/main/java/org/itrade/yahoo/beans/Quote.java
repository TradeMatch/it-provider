
package org.itrade.yahoo.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "Adj_Close",
    "Close",
    "Date",
    "High",
    "Low",
    "Open",
    "Symbol",
    "Volume"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    @JsonProperty("Adj_Close")
    private Float AdjClose;
    @JsonProperty("Close")
    private Float Close;
    @JsonProperty("Date")
    private String Date;
    @JsonProperty("High")
    private Float High;
    @JsonProperty("Low")
    private Float Low;
    @JsonProperty("Open")
    private Float Open;
    @JsonProperty("Symbol")
    private String Symbol;
    @JsonProperty("Volume")
    private Integer Volume;

    @Id
    @JsonProperty("_id")
    public String getId() {
        return getSymbol() + "_" + getDate();
    }

    /**
     *
     * @return
     *     The AdjClose
     */
    @JsonProperty("Adj_Close")
    public Float getAdjClose() {
        return AdjClose;
    }

    /**
     *
     * @param AdjClose
     *     The Adj_Close
     */
    @JsonProperty("Adj_Close")
    public void setAdjClose(Float AdjClose) {
        this.AdjClose = AdjClose;
    }

    /**
     *
     * @return
     *     The Close
     */
    @JsonProperty("Close")
    public Float getClose() {
        return Close;
    }

    /**
     *
     * @param Close
     *     The Close
     */
    @JsonProperty("Close")
    public void setClose(Float Close) {
        this.Close = Close;
    }

    /**
     *
     * @return
     *     The Date
     */
    @JsonProperty("Date")
    public String getDate() {
        return Date;
    }

    /**
     *
     * @param Date
     *     The Date
     */
    @JsonProperty("Date")
    public void setDate(String Date) {
        this.Date = Date;
    }

    /**
     *
     * @return
     *     The High
     */
    @JsonProperty("High")
    public Float getHigh() {
        return High;
    }

    /**
     *
     * @param High
     *     The High
     */
    @JsonProperty("High")
    public void setHigh(Float High) {
        this.High = High;
    }

    /**
     *
     * @return
     *     The Low
     */
    @JsonProperty("Low")
    public Float getLow() {
        return Low;
    }

    /**
     *
     * @param Low
     *     The Low
     */
    @JsonProperty("Low")
    public void setLow(Float Low) {
        this.Low = Low;
    }

    /**
     *
     * @return
     *     The Open
     */
    @JsonProperty("Open")
    public Float getOpen() {
        return Open;
    }

    /**
     *
     * @param Open
     *     The Open
     */
    @JsonProperty("Open")
    public void setOpen(Float Open) {
        this.Open = Open;
    }

    /**
     *
     * @return
     *     The Symbol
     */
    @JsonProperty("Symbol")
    public String getSymbol() {
        return Symbol;
    }

    /**
     *
     * @param Symbol
     *     The Symbol
     */
    @JsonProperty("Symbol")
    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }

    /**
     *
     * @return
     *     The Volume
     */
    @JsonProperty("Volume")
    public Integer getVolume() {
        return Volume;
    }

    /**
     *
     * @param Volume
     *     The Volume
     */
    @JsonProperty("Volume")
    public void setVolume(Integer Volume) {
        this.Volume = Volume;
    }

}
