package org.itrade.benzinga.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

/*
    {
        "action_company": "Maintains",
        "action_pt": "Raises",
        "analyst": "CRT Capital",
        "date": "2014-10-24",
        "exchange": "NYSE",
        "id": "544a6b974309aa04e2347907",
        "importance": "",
        "name": "American Airlines",
        "pt_current": "54.000",
        "pt_prior": "48.000",
        "ptpo": "PT",
        "rating_current": "Buy",
        "rating_prior": "",
        "ticker": "AAL",
        "time": "09:00:00",
        "updated": 1414163372,
        "url": "http://www.benzinga.com/stock/AAL/ratings",
        "url_calendar": "http://www.benzinga.com/stock/AAL/ratings",
        "url_news": "http://www.benzinga.com/stock-articles/AAL/analyst-ratings"
    }
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BenzingaRating {

    @Id @ObjectId
    @JsonProperty("id")
    public String id;

    @JsonProperty("date")
    public String date;

    @JsonProperty("url_calendar")
    public String urlCalendar;

    @JsonProperty("url_news")
    public String urlNews;

    @JsonProperty("time")
    public String time;

    @JsonProperty("ticker")
    public String ticker;

    @JsonProperty("exchange")
    public String exchange;

    @JsonProperty("name")
    public String name;

    @JsonProperty("action_pt")
    public String actionPt;

    @JsonProperty("action_company")
    public String actionCompany;

    @JsonProperty("ptpo")
    public String ptpo;

    @JsonProperty("analyst")
    public String analyst;

    @JsonProperty("rating_current")
    public String ratingCurrent;

    @JsonProperty("pt_current")
    public String ptCurrent;

    @JsonProperty("rating_prior")
    public String ratingPrior;

    @JsonProperty("pt_prior")
    public String ptPrior;

    @JsonProperty("url")
    public String url;

    @JsonProperty("importance")
    public String importance;

    @JsonProperty("updated")
    public String updated;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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

    public String getPtCurrent() {
        return ptCurrent;
    }

    public void setPtCurrent(String ptCurrent) {
        this.ptCurrent = ptCurrent;
    }

    public String getRatingPrior() {
        return ratingPrior;
    }

    public void setRatingPrior(String ratingPrior) {
        this.ratingPrior = ratingPrior;
    }

    public String getPtPrior() {
        return ptPrior;
    }

    public void setPtPrior(String ptPrior) {
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

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
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
