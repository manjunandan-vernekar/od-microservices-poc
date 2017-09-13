package com.officedepot;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.officedepot.domain.price.PriceRow;

@Component
public class Receiver {

	@JmsListener(destination = "feeds/out/1.0/price/contract")
    public void receiveMessage(Map<String, Object> segments) {
        System.out.println("Importing message number " + segments.get("messageNumber"));
        
        @SuppressWarnings("unchecked")
		List<LinkedHashMap<String, Object>> priceRows = (ArrayList<LinkedHashMap<String, Object>>) segments.get("segments");
        
        Config config = new Config();
		config.useSingleServer()
		  .setAddress("127.0.0.1:6379");
		RedissonClient client = Redisson.create(config);
        
        //Jedis jedis = new Jedis("localhost");
		for (int i=0; i<priceRows.size(); i++){
			LinkedHashMap<String, Object> priceRow = priceRows.get(i); 
			PriceRow priceRowObj = new PriceRow(priceRow.get("price").toString(), priceRow.get("currency").toString(),priceRow.get("accountNumber").toString(),priceRow.get("quantity").toString(),priceRow.get("sku").toString(),priceRow.get("unitOfMeasure").toString());
			RBucket<PriceRow> bucket = client.getBucket("productPriceData:"+ priceRowObj.getSku() +":"+priceRowObj.getCurrency()+":"+priceRowObj.getAccountNumber());
			bucket.set(priceRowObj);
			/*try {
			     ByteArrayOutputStream bo = new ByteArrayOutputStream();
			     ObjectOutputStream so = new ObjectOutputStream(bo);
			     so.writeObject(priceRowObj);
			     so.flush();
			     String serializedObject = new String(Base64.encodeBase64(bo.toByteArray()));
			     jedis.set("productPriceData:"+ priceRowObj.getSku() +":"+priceRowObj.getCurrency()+":"+priceRowObj.getAccountNumber(), serializedObject);
			 } catch (Exception e) {
			     System.out.println(e);
			 }*/
		}
		//jedis.close();
		
		
		
		System.out.println("...Done");
        
    }

}
