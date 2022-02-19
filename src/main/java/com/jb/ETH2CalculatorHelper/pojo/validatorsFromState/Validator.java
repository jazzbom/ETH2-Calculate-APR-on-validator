
package com.jb.ETH2CalculatorHelper.pojo.validatorsFromState;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pubkey",
    "withdrawal_credentials",
    "effective_balance",
    "slashed",
    "activation_eligibility_epoch",
    "activation_epoch",
    "exit_epoch",
    "withdrawable_epoch"
})
@Generated("jsonschema2pojo")
public class Validator implements Serializable
{

    @JsonProperty("pubkey")
    private String pubkey;
    @JsonProperty("withdrawal_credentials")
    private String withdrawalCredentials;
    @JsonProperty("effective_balance")
    private String effectiveBalance;
    @JsonProperty("slashed")
    private Boolean slashed;
    @JsonProperty("activation_eligibility_epoch")
    private String activationEligibilityEpoch;
    @JsonProperty("activation_epoch")
    private String activationEpoch;
    @JsonProperty("exit_epoch")
    private String exitEpoch;
    @JsonProperty("withdrawable_epoch")
    private String withdrawableEpoch;
    private final static long serialVersionUID = 4207062780314224751L;

    @JsonProperty("pubkey")
    public String getPubkey() {
        return pubkey;
    }

    @JsonProperty("pubkey")
    public void setPubkey(String pubkey) {
        this.pubkey = pubkey;
    }

    @JsonProperty("withdrawal_credentials")
    public String getWithdrawalCredentials() {
        return withdrawalCredentials;
    }

    @JsonProperty("withdrawal_credentials")
    public void setWithdrawalCredentials(String withdrawalCredentials) {
        this.withdrawalCredentials = withdrawalCredentials;
    }

    @JsonProperty("effective_balance")
    public String getEffectiveBalance() {
        return effectiveBalance;
    }

    @JsonProperty("effective_balance")
    public void setEffectiveBalance(String effectiveBalance) {
        this.effectiveBalance = effectiveBalance;
    }

    @JsonProperty("slashed")
    public Boolean getSlashed() {
        return slashed;
    }

    @JsonProperty("slashed")
    public void setSlashed(Boolean slashed) {
        this.slashed = slashed;
    }

    @JsonProperty("activation_eligibility_epoch")
    public String getActivationEligibilityEpoch() {
        return activationEligibilityEpoch;
    }

    @JsonProperty("activation_eligibility_epoch")
    public void setActivationEligibilityEpoch(String activationEligibilityEpoch) {
        this.activationEligibilityEpoch = activationEligibilityEpoch;
    }

    @JsonProperty("activation_epoch")
    public String getActivationEpoch() {
        return activationEpoch;
    }

    @JsonProperty("activation_epoch")
    public void setActivationEpoch(String activationEpoch) {
        this.activationEpoch = activationEpoch;
    }

    @JsonProperty("exit_epoch")
    public String getExitEpoch() {
        return exitEpoch;
    }

    @JsonProperty("exit_epoch")
    public void setExitEpoch(String exitEpoch) {
        this.exitEpoch = exitEpoch;
    }

    @JsonProperty("withdrawable_epoch")
    public String getWithdrawableEpoch() {
        return withdrawableEpoch;
    }

    @JsonProperty("withdrawable_epoch")
    public void setWithdrawableEpoch(String withdrawableEpoch) {
        this.withdrawableEpoch = withdrawableEpoch;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Validator.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("pubkey");
        sb.append('=');
        sb.append(((this.pubkey == null)?"<null>":this.pubkey));
        sb.append(',');
        sb.append("withdrawalCredentials");
        sb.append('=');
        sb.append(((this.withdrawalCredentials == null)?"<null>":this.withdrawalCredentials));
        sb.append(',');
        sb.append("effectiveBalance");
        sb.append('=');
        sb.append(((this.effectiveBalance == null)?"<null>":this.effectiveBalance));
        sb.append(',');
        sb.append("slashed");
        sb.append('=');
        sb.append(((this.slashed == null)?"<null>":this.slashed));
        sb.append(',');
        sb.append("activationEligibilityEpoch");
        sb.append('=');
        sb.append(((this.activationEligibilityEpoch == null)?"<null>":this.activationEligibilityEpoch));
        sb.append(',');
        sb.append("activationEpoch");
        sb.append('=');
        sb.append(((this.activationEpoch == null)?"<null>":this.activationEpoch));
        sb.append(',');
        sb.append("exitEpoch");
        sb.append('=');
        sb.append(((this.exitEpoch == null)?"<null>":this.exitEpoch));
        sb.append(',');
        sb.append("withdrawableEpoch");
        sb.append('=');
        sb.append(((this.withdrawableEpoch == null)?"<null>":this.withdrawableEpoch));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
