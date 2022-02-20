package com.jb.ETH2CalculatorHelper.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.jb.ETH2CalculatorHelper.pojo.stateFinalityCheckpoint.GetStateFinality;
import com.jb.ETH2CalculatorHelper.pojo.validatorsFromState.BlockDataPojo;
import com.jb.ETH2CalculatorHelper.utils.WebClientBuilder;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Eth2ValidatorService {
	@Value("${second_per_slot}")
	private BigInteger slotsPerSeconds;

	@Value("${slot_per_epoch}")
	private BigInteger slotsPerEpoch;

	@Value("${infuraEndPoint}")
	private String infuraEndPoint;

	@Value("${infuraUserId}")
	private String infuraUserId;

	@Value("${infuraPassword}")
	private String infuraPassword;

	public static MathContext MATH_CTX = new MathContext(19, RoundingMode.HALF_UP);
	public static final int MATH_SCALE = 2;

	@SneakyThrows
	public BigDecimal calculateAPRForValidator(BigInteger index, BigInteger ageInSeconds) {
		long t0 = System.currentTimeMillis();
		log.debug("calculateAPRForValidator begin calc: Validator {} at time {}", index, t0);
		
		BigDecimal apr = null;
		CompletableFuture<BigInteger> fcurrentNetworkEpoch = getEpochOfFinalizedChain();
		CompletableFuture<BigDecimal> fcurrentBalanceOfValidator = getValidatorBalance(index, "finalized", "active_ongoing");
		CompletableFuture.allOf(fcurrentNetworkEpoch, fcurrentBalanceOfValidator);
		try {
			BigInteger currentNetworkEpoch = fcurrentNetworkEpoch.get();
			BigDecimal currentBalanceOfValidator = fcurrentBalanceOfValidator.get();
			BigInteger currentSlot = currentNetworkEpoch.multiply(slotsPerEpoch);
			BigInteger secsPerEpoch = slotsPerEpoch.multiply(slotsPerSeconds);
			BigInteger pastSlot = currentSlot.subtract(((ageInSeconds.divide(secsPerEpoch)).multiply(slotsPerEpoch)));

			CompletableFuture<BigDecimal> fpastBalanceOfValidator = 
					getValidatorBalance(index, String.valueOf(pastSlot), "active_ongoing");
			CompletableFuture.anyOf(fpastBalanceOfValidator);
			BigDecimal pastBalanceOfValidator = fpastBalanceOfValidator.get();
			BigDecimal interestEarned = currentBalanceOfValidator.subtract(pastBalanceOfValidator);
			log.info("calculateAPRForValidator interestEarned Gwei {}.", interestEarned);
			apr = calculateAPR(pastBalanceOfValidator, interestEarned);
			
			long t1 = System.currentTimeMillis() - t0;;
			log.debug("calculateAPRForValidator success in time {}ms for Validator {} at APR {}.", t1, index, apr);
		} catch (InterruptedException | ExecutionException e) {
			log.error("Error at Eth2ValidatorService - calculateAPRForValidator for Validator {}", index, e);
			throw new Exception(e);
		}
		return apr;
	}

	public BigDecimal calculateAPR(BigDecimal pastBalanceOfValidator, BigDecimal interestEarned) {
		BigDecimal apr;
		apr = (interestEarned.divide(pastBalanceOfValidator, MATH_CTX))
				.multiply(new BigDecimal(100), MATH_CTX).setScale(MATH_SCALE, RoundingMode.HALF_UP);
		return apr;
	}

	@Async
	public CompletableFuture<BigInteger> getEpochOfFinalizedChain() throws WebClientResponseException {
		log.debug("getEpochOfFinalizedChain");
		String uriResourcePath = "/eth/v1/beacon/states/finalized/finality_checkpoints";
		String uri = resourceURIBuilder(uriResourcePath);
		String apiKeyBase64Encoded = getApiKey();
		GetStateFinality gs = WebClientBuilder.getInstance().get().uri(uri).header("Authorization", apiKeyBase64Encoded)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(GetStateFinality.class).block();

		String epochString = gs.getData().getFinalized().getEpoch();
		BigInteger epoch = new BigInteger(epochString);
		log.info("getEpochOfFinalizedChain epoch: {}", epoch);
		return CompletableFuture.completedFuture(epoch);
	}

	@Async
	public CompletableFuture<BigDecimal> getValidatorBalance(BigInteger index, String state, String status)
			throws WebClientResponseException {
		log.debug("getValidatorBalance for validatorIndex {} state {} status {}", index, state, status);
		String uriResourcePath = "/eth/v1/beacon/states/" + state + "/validators?id=" + index + "&status="
				+ status;
		String uri = resourceURIBuilder(uriResourcePath);
		String apiKeyBase64Encoded = getApiKey();
		BlockDataPojo bd = WebClientBuilder.getInstance().get().uri(uri).header("Authorization", apiKeyBase64Encoded)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(BlockDataPojo.class).block();

		String balString = bd.getData().get(0).getBalance();
		log.info("getValidatorBalance at state {} validatorIndex: {} was {}", state, index, balString);
		return CompletableFuture.completedFuture(new BigDecimal(balString));
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
