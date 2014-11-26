package org.itrade.benzinga.beans;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "date",
        "time",
        "country",
        "event_name",
        "event_period",
        "period",
        "period_year",
        "actual",
        "consensus",
        "prior",
        "importance",
        "updated",
        "description"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Economic {

    @Id
    @ObjectId
    @JsonProperty("id")
    private String id;
    @JsonProperty("date")
    private String date;
    @JsonProperty("time")
    private String time;
    @JsonProperty("country")
    private String country;
    @JsonProperty("event_name")
    private String eventName;
    @JsonProperty("event_period")
    private String eventPeriod;
    @JsonProperty("period")
    private String period;
    @JsonProperty("period_year")
    private String periodYear;
    @JsonProperty("actual")
    private String actual;
    @JsonProperty("consensus")
    private String consensus;
    @JsonProperty("prior")
    private String prior;
    @JsonProperty("importance")
    private String importance;
    @JsonProperty("updated")
    private Integer updated;
    @JsonProperty("description")
    private String description;

    /**
     *
     * @return
     * The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The date
     */
    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The time
     */
    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    /**
     *
     * @param time
     * The time
     */
    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    /**
     *
     * @return
     * The country
     */
    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The country
     */
    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     * The eventName
     */
    @JsonProperty("event_name")
    public String getEventName() {
        return eventName;
    }

    /**
     *
     * @param eventName
     * The event_name
     */
    @JsonProperty("event_name")
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     *
     * @return
     * The eventPeriod
     */
    @JsonProperty("event_period")
    public String getEventPeriod() {
        return eventPeriod;
    }

    /**
     *
     * @param eventPeriod
     * The event_period
     */
    @JsonProperty("event_period")
    public void setEventPeriod(String eventPeriod) {
        this.eventPeriod = eventPeriod;
    }

    /**
     *
     * @return
     * The period
     */
    @JsonProperty("period")
    public String getPeriod() {
        return period;
    }

    /**
     *
     * @param period
     * The period
     */
    @JsonProperty("period")
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     *
     * @return
     * The periodYear
     */
    @JsonProperty("period_year")
    public String getPeriodYear() {
        return periodYear;
    }

    /**
     *
     * @param periodYear
     * The period_year
     */
    @JsonProperty("period_year")
    public void setPeriodYear(String periodYear) {
        this.periodYear = periodYear;
    }

    /**
     *
     * @return
     * The actual
     */
    @JsonProperty("actual")
    public String getActual() {
        return actual;
    }

    /**
     *
     * @param actual
     * The actual
     */
    @JsonProperty("actual")
    public void setActual(String actual) {
        this.actual = actual;
    }

    /**
     *
     * @return
     * The consensus
     */
    @JsonProperty("consensus")
    public String getConsensus() {
        return consensus;
    }

    /**
     *
     * @param consensus
     * The consensus
     */
    @JsonProperty("consensus")
    public void setConsensus(String consensus) {
        this.consensus = consensus;
    }

    /**
     *
     * @return
     * The prior
     */
    @JsonProperty("prior")
    public String getPrior() {
        return prior;
    }

    /**
     *
     * @param prior
     * The prior
     */
    @JsonProperty("prior")
    public void setPrior(String prior) {
        this.prior = prior;
    }

    /**
     *
     * @return
     * The importance
     */
    @JsonProperty("importance")
    public String getImportance() {
        return importance;
    }

    /**
     *
     * @param importance
     * The importance
     */
    @JsonProperty("importance")
    public void setImportance(String importance) {
        this.importance = importance;
    }

    /**
     *
     * @return
     * The updated
     */
    @JsonProperty("updated")
    public Integer getUpdated() {
        return updated;
    }

    /**
     *
     * @param updated
     * The updated
     */
    @JsonProperty("updated")
    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    /**
     *
     * @return
     * The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

}