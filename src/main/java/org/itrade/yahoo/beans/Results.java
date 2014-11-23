
package org.itrade.yahoo.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "quote"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Results {

    @JsonProperty("quote")
    private List<Quote> quote = new ArrayList<Quote>();

    /**
     * 
     * @return
     *     The quote
     */
    @JsonProperty("quote")
    public List<Quote> getQuote() {
        return quote;
    }

    /**
     * 
     * @param quote
     *     The quote
     */
    @JsonProperty("quote")
    public void setQuote(List<Quote> quote) {
        this.quote = quote;
    }

}
