package org.itrade.benzinga.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import java.time.LocalDate;
import java.time.LocalTime;

/*
    {
        "id": "545d289a4309aa04e234947b",
        "date": "2014-11-07",
        "url_calendar": "http://www.benzinga.com/stock/AAP/ratings",
        "url_news": "http://www.benzinga.com/stock-articles/AAP/analyst-ratings",
        "time": "09:00:00",
        "ticker": "AAP",
        "exchange": "NYSE",
        "name": "Advance Auto Parts Inc",
        "action_pt": "Raises",
        "action_company": "Maintains",
        "ptpo": "PT",
        "analyst": "Citigroup",
        "rating_current": "Neutral",
        "pt_current": "145.000",
        "rating_prior": "",
        "pt_prior": "132.000",
        "url": "http://www.benzinga.com/stock/AAP/ratings",
        "importance": "",
        "updated": 1415391404
    }

 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BenzingaRating {

    @Id @ObjectId
    @JsonProperty("id")
    private String id;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("url_calendar")
    private String urlCalendar;

    @JsonProperty("url_news")
    private String urlNews;

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonProperty("time")
    private LocalTime time;

    @JsonProperty("ticker")
    private String ticker;

    @JsonProperty("exchange")
    private String exchange;

    @JsonProperty("name")
    private String name;

    @JsonProperty("action_pt")
    private String actionPt;

    @JsonProperty("action_company")
    private String actionCompany;

    @JsonProperty("ptpo")
    private String ptpo;

    @JsonProperty("analyst")
    private String analyst;

    @JsonProperty("rating_current")
    private String ratingCurrent;

    @JsonProperty("pt_current")
    private Float ptCurrent;

    @JsonProperty("rating_prior")
    private String ratingPrior;

    @JsonProperty("pt_prior")
    private Float ptPrior;

    @JsonProperty("url")
    private String url;

    @JsonProperty("importance")
    private String importance;

    @JsonProperty("updated")
    private long updated;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUrlCalendar() {
        return urlCalendar;
    }

    public void setUrlCalendar(String urlCalendar) {
        this.urlCalendar = urlCalendar;
    }

    public String getUrlNews() {
        return urlNews;
    }

    public void setUrlNews(String urlNews) {
        this.urlNews = urlNews;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActionPt() {
        return actionPt;
    }

    public void setActionPt(String actionPt) {
        this.actionPt = actionPt;
    }

    public String getActionCompany() {
        return actionCompany;
    }

    public void setActionCompany(String actionCompany) {
        this.actionCompany = actionCompany;
    }

    public String getPtpo() {
        return ptpo;
    }

    public void setPtpo(String ptpo) {
        this.ptpo = ptpo;
    }

    public String getAnalyst() {
        return analyst;
    }

    public void setAnalyst(String analyst) {
        this.analyst = analyst;
    }

    public String getRatingCurrent() {
        return ratingCurrent;
    }

    public void setRatingCurrent(String ratingCurrent) {
        this.ratingCurrent = ratingCurrent;
    }

    public Float getPtCurrent() {
        return ptCurrent;
    }

    public void setPtCurrent(Float ptCurrent) {
        this.ptCurrent = ptCurrent;
    }

    public String getRatingPrior() {
        return ratingPrior;
    }

    public void setRatingPrior(String ratingPrior) {
        this.ratingPrior = ratingPrior;
    }

    public Float getPtPrior() {
        return ptPrior;
    }

    public void setPtPrior(Float ptPrior) {
        this.ptPrior = ptPrior;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "BenzingaRating{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", urlCalendar='" + urlCalendar + '\'' +
                ", urlNews='" + urlNews + '\'' +
                ", time='" + time + '\'' +
                ", ticker='" + ticker + '\'' +
                ", exchange='" + exchange + '\'' +
                ", name='" + name + '\'' +
                ", actionPt='" + actionPt + '\'' +
                ", actionCompany='" + actionCompany + '\'' +
                ", ptpo='" + ptpo + '\'' +
                ", analyst='" + analyst + '\'' +
                ", ratingCurrent='" + ratingCurrent + '\'' +
                ", ptCurrent='" + ptCurrent + '\'' +
                ", ratingPrior='" + ratingPrior + '\'' +
                ", ptPrior='" + ptPrior + '\'' +
                ", url='" + url + '\'' +
                ", importance='" + importance + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}
