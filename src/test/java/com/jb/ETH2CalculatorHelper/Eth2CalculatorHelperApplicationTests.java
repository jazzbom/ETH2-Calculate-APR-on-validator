package com.jb.ETH2CalculatorHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.DisplayName;
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
	@DisplayName("Test to perform a full validation of the calculateAPRForValidator service.")
	void testCalculateAPRForValidator() throws WebClientResponseException, IOException {
		BigDecimal apr = eth2ValidatorService.calculateAPRForValidator(BigInteger.valueOf(33548),
												BigInteger.valueOf(31536000l));
		assertTrue(apr != null, "calculateAPRForValidator service call was unsuccessful.");
	}
	
	@Test
	@DisplayName("Simple test to check the formula of the APR calculator.")
	void testCalculateAPR() {
		BigDecimal apr = eth2ValidatorService.calculateAPR(new BigDecimal(166), new BigDecimal(24.56));
		assertEquals(14.80, apr.doubleValue(), "APR calc function expects 14.79.");
	}
	
	@Test
	@DisplayName("Test for Bad Request Exception.")
	void exceptionTesting() {
	    Throwable exception = assertThrows(WebClientResponseException.class, () 
	    		-> eth2ValidatorService.calculateAPRForValidator(BigInteger.valueOf(999999999999l),
	    									BigInteger.valueOf(999999999999l)));
	    assertTrue(exception.getMessage().contains("Bad Request"), "Expecting Bad Request");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
