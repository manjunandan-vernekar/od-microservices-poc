package com.officedepot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.redisson.api.RBuckets;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import com.officedepot.domain.price.PriceRow;
import com.officedepot.integration.RedisConnector;

public class ContractPricingService {
	@Autowired
	RedisConnector redisConnector;
	
	public List<PriceRow> getContractPrices(String[] skus){
		RedissonClient client = redisConnector.getRedissonClient();
		List<PriceRow> contractPrices = new ArrayList<PriceRow>();
		
		RBuckets rbuckets = client.getBuckets();
		Map<String, PriceRow> receivedBuckets = rbuckets.get(skus);
		contractPrices.addAll(receivedBuckets.values());

		return contractPrices;
	}

}
