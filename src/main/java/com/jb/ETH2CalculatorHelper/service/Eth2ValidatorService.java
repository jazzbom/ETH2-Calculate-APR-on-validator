package com.jb.ETH2CalculatorHelper.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.jb.ETH2CalculatorHelper.pojo.stateFinalityCheckpoint.GetStateFinality;
import com.jb.ETH2CalculatorHelper.pojo.validatorsFromState.BlockDataPojo;
import com.jb.ETH2CalculatorHelper.utils.WebClientBuilder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Eth2ValidatorService {
	@Value("${second_per_slot}")
	private long slotsPerSeconds;

	@Value("${slot_per_epoch}")
	private long slotsPerEpoch;

	@Value("${infuraEndPoint}")
	private String infuraEndPoint;

	@Value("${infuraUserId}")
	private String infuraUserId;

	@Value("${infuraPassword}")
	private String infuraPassword;

	public static MathContext MATH_CTX = new MathContext(19, RoundingMode.HALF_UP);
	public static final int MATH_SCALE = 2;

	public double calculateAPRForValidator(long validatorIndex, long ageInSeconds)
			throws WebClientResponseException {
		log.debug("calculateAPRForValidator begin calc: Validator {}.", validatorIndex);
		long currentNetworkEpoch = getEpochOfFinalizedChain();
		long currentBalanceOfValidator = getValidatorBalance(validatorIndex, "finalized", "active_ongoing");

		long currentSlot = currentNetworkEpoch * slotsPerEpoch;
		long secsPerEpoch = slotsPerEpoch * slotsPerSeconds;
		long pastSlot = currentSlot - ((ageInSeconds / secsPerEpoch) * slotsPerEpoch);

		long pastBalanceOfValidator = getValidatorBalance(validatorIndex, String.valueOf(pastSlot), "active_ongoing");

		long interestEarned = currentBalanceOfValidator - pastBalanceOfValidator;
		log.info("calculateAPRForValidator interestEarned Gwei {}.", interestEarned);
		BigDecimal apr = (new BigDecimal(interestEarned).divide(new BigDecimal(pastBalanceOfValidator), MATH_CTX))
				.multiply(new BigDecimal(100), MATH_CTX).setScale(MATH_SCALE, RoundingMode.HALF_UP);
		log.info("calculateAPRForValidator success: Validator {} at APR {}.", validatorIndex, apr);
		return apr.doubleValue();
	}

	public long getEpochOfFinalizedChain() throws WebClientResponseException {
		log.debug("getEpochOfFinalizedChain");
		String uriResourcePath = "/eth/v1/beacon/states/finalized/finality_checkpoints";
		String uri = resourceURIBuilder(uriResourcePath);
		String apiKeyBase64Encoded = getApiKey();
		GetStateFinality gs = WebClientBuilder.getInstance().get().uri(uri).header("Authorization", apiKeyBase64Encoded)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(GetStateFinality.class).block();

		String epochString = gs.getData().getFinalized().getEpoch();
		Long epoch = Long.parseLong(epochString);
		log.info("getEpochOfFinalizedChain epoch: {}", epoch);
		return epoch;
	}

	public long getValidatorBalance(long validatorIndex, String state, String status)
			throws WebClientResponseException {
		log.debug("getValidatorBalance for validatorIndex {} state {} status {}", validatorIndex, state, status);
		String uriResourcePath = "/eth/v1/beacon/states/" + state + "/validators?id=" + validatorIndex + "&status="
				+ status;
		String uri = resourceURIBuilder(uriResourcePath);
		String apiKeyBase64Encoded = getApiKey();
		BlockDataPojo bd = WebClientBuilder.getInstance().get().uri(uri).header("Authorization", apiKeyBase64Encoded)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(BlockDataPojo.class).block();

		String balString = bd.getData().get(0).getBalance();
		log.info("getValidatorBalance at state {} validatorIndex: {} was {}", state, validatorIndex, balString);
		return Long.parseLong(balString);
	}

	private String getApiKey() {
		String key = infuraUserId + ":" + infuraPassword;
		String apiKeyBase64Encoded = "Basic " + Base64.getEncoder().encodeToString(key.getBytes());
		return apiKeyBase64Encoded;
	}

	private String resourceURIBuilder(String uriResourcePath) {
		String url = new StringBuilder(infuraEndPoint).append(uriResourcePath).toString();
		return url;
	}

}
