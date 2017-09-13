package com.officedepot.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.officedepot.domain.price.PriceRow;
import com.officedepot.service.ContractPricingService;

public class ContractPricingFacade {
	
	@Autowired
	ContractPricingService contractPricingService;
	
	public List<PriceRow> getContractPrices(String[] skus){
		return contractPricingService.getContractPrices(skus);
	}

}
