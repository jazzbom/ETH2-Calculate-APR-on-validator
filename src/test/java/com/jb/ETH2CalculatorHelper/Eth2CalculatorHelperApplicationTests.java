package com.jb.ETH2CalculatorHelper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.jb.ETH2CalculatorHelper.service.Eth2ValidatorService;

@SpringBootTest
class Eth2CalculatorHelperApplicationTests {
	
	@Autowired
	private Eth2ValidatorService eth2ValidatorService;
	
	@Test
	void contextLoads() {
	}

	@Test
	void testCalculateAPRForValidator() throws WebClientResponseException, IOException {
		double apr = eth2ValidatorService.calculateAPRForValidator(33548l, 31536000l);
		assertTrue(apr != 0.00, "Passed");
	}
	
}
