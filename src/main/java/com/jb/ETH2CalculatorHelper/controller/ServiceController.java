package com.jb.ETH2CalculatorHelper.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jb.ETH2CalculatorHelper.service.Eth2ValidatorService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("eth2")
@Slf4j
@Api(value="ServiceController", description="Operations pertaining to ETH2 validator.")
public class ServiceController {

	@Autowired
	private Eth2ValidatorService eth2ValidatorService;
	
	@RequestMapping(value = "/validator/{index}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map calculateAPRForValidator(@PathVariable long index,
			@RequestParam(required = true) long ageInSeconds) {
		log.debug("calculateAPRForValidator index: {}, ageLookUp: {}", index, ageInSeconds);
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put("success", false);
		
		try {
			double apr = eth2ValidatorService.calculateAPRForValidator(index, ageInSeconds);
			result.put("validator", index);
			result.put("apr", apr);
			result.put("success", true);
		}catch (Exception e) {
			log.error("Error at calculateAPRForValidator for validator {} with age lookup of {}", index, ageInSeconds, e);
		}
		log.debug("calculateAPRForValidator response index: {}, ageLookUp: {} was", index, ageInSeconds, result);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
