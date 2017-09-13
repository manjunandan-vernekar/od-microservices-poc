package com.officedepot.integration;

import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.core.io.ClassPathResource;

public class RedisConnector {
	private static Config config = null;
	private static RedissonClient client = null;
	
	public void initRedisConnection(){
		try {
			if (client == null){
	            config = Config.fromJSON(new ClassPathResource("singleServerConfig.json").getFile());
	            client = Redisson.create(config);	
			}
        }catch(IOException ioe) {
        	System.out.println(ioe.getMessage());
        }
	}
	
	public RedissonClient getRedissonClient(){
		return RedisConnector.client;
	}

}
