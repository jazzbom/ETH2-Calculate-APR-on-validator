
package com.jb.ETH2CalculatorHelper.pojo.stateFinalityCheckpoint;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "previous_justified",
    "current_justified",
    "finalized"
})
@Generated("jsonschema2pojo")
public class Data implements Serializable
{

    @JsonProperty("previous_justified")
    private PreviousJustified previousJustified;
    @JsonProperty("current_justified")
    private CurrentJustified currentJustified;
    @JsonProperty("finalized")
    private Finalized finalized;
    private final static long serialVersionUID = -3042838864908101645L;

    @JsonProperty("previous_justified")
    public PreviousJustified getPreviousJustified() {
        return previousJustified;
    }

    @JsonProperty("previous_justified")
    public void setPreviousJustified(PreviousJustified previousJustified) {
        this.previousJustified = previousJustified;
    }

    @JsonProperty("current_justified")
    public CurrentJustified getCurrentJustified() {
        return currentJustified;
    }

    @JsonProperty("current_justified")
    public void setCurrentJustified(CurrentJustified currentJustified) {
        this.currentJustified = currentJustified;
    }

    @JsonProperty("finalized")
    public Finalized getFinalized() {
        return finalized;
    }

    @JsonProperty("finalized")
    public void setFinalized(Finalized finalized) {
        this.finalized = finalized;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Data.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("previousJustified");
        sb.append('=');
        sb.append(((this.previousJustified == null)?"<null>":this.previousJustified));
        sb.append(',');
        sb.append("currentJustified");
        sb.append('=');
        sb.append(((this.currentJustified == null)?"<null>":this.currentJustified));
        sb.append(',');
        sb.append("finalized");
        sb.append('=');
        sb.append(((this.finalized == null)?"<null>":this.finalized));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
