package com.officedepot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.officedepot.domain.price.PriceRow;
import com.officedepot.facade.ContractPricingFacade;

@RestController
public class MicroServiceController {

	@Autowired
	ContractPricingFacade pricingFacade;
	
	@RequestMapping(value = "/homepage", 
			method = RequestMethod.POST)
	@ResponseBody
	public List<PriceRow> viewHomepage(@RequestParam(value = "keys") String[] keys){
		return pricingFacade.getContractPrices(keys);
	}
}
