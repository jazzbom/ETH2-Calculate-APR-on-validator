package com.jb.ETH2CalculatorHelper.utils;

import org.springframework.web.reactive.function.client.WebClient;

public class WebClientBuilder {
	
	private static WebClient webClient;
	
	public synchronized static WebClient getInstance() {
	    if (webClient == null) {
	    	webClient = WebClient.builder().build();
	    }
	    return webClient;
	}
	
	
	
}
