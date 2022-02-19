
package com.jb.ETH2CalculatorHelper.pojo.stateFinalityCheckpoint;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "epoch",
    "root"
})
@Generated("jsonschema2pojo")
public class CurrentJustified implements Serializable
{

    @JsonProperty("epoch")
    private String epoch;
    @JsonProperty("root")
    private String root;
    private final static long serialVersionUID = 3753547476565358105L;

    @JsonProperty("epoch")
    public String getEpoch() {
        return epoch;
    }

    @JsonProperty("epoch")
    public void setEpoch(String epoch) {
        this.epoch = epoch;
    }

    @JsonProperty("root")
    public String getRoot() {
        return root;
    }

    @JsonProperty("root")
    public void setRoot(String root) {
        this.root = root;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CurrentJustified.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("epoch");
        sb.append('=');
        sb.append(((this.epoch == null)?"<null>":this.epoch));
        sb.append(',');
        sb.append("root");
        sb.append('=');
        sb.append(((this.root == null)?"<null>":this.root));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
