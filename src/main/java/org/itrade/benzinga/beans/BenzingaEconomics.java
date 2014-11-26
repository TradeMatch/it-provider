package org.itrade.benzinga.beans;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "economics"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class BenzingaEconomics {

    @JsonProperty("economics")
    private List<Economic> economics = new ArrayList<Economic>();

    /**
     * @return The economics
     */
    @JsonProperty("economics")
    public List<Economic> getEconomics() {
        return economics;
    }

    /**
     * @param economics The economics
     */
    @JsonProperty("economics")
    public void setEconomics(List<Economic> economics) {
        this.economics = economics;
    }
}