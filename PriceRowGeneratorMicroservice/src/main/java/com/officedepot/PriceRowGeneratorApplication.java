package com.officedepot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.officedepot.domain.price.PriceRow;

@SpringBootApplication
@EnableJms
public class PriceRowGeneratorApplication {

    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    public static void main(String[] args) {
        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(PriceRowGeneratorApplication.class, args);
        

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        
        int totalPriceRows = 1000;
        int recordsPerMessage = 1000;
        int totalMessages = totalPriceRows/recordsPerMessage;
        
        for(int i=0; i<totalMessages; i++){
        	List<PriceRow> priceRowList = new ArrayList<PriceRow>();
        	for (int j=0; j<recordsPerMessage; j++){
        		priceRowList.add(new PriceRow(j, "EUR", i + "", 10, "" + j, "each"));
        	}
            Map<String, Object> segments = new HashMap<String, Object>();
            segments.put("segments", priceRowList);
            segments.put("country", "AT");
            segments.put("messageNumber", i);
            segments.put("exportTime", "2017-05-09 11:49:45");
            System.out.println("Sending message " + i);
            jmsTemplate.convertAndSend("feeds/out/1.0/price/contract", segments, new MessagePostProcessor() {
                public Message postProcessMessage(Message message) throws JMSException {
                	message.setJMSCorrelationID("9983fe64-3328-4afd-92f2-6fe865037b01");
                	message.setJMSDeliveryMode(1);
                	
                	message.setBooleanProperty("FULL_FEED", true);
                	message.setStringProperty("FEED_TYPE", "ContractPrice");
                	message.setIntProperty("GROUP_SIZE", 1);
                	message.setStringProperty("MULE_CORRELATION_ID", "9983fe64-3328-4afd-92f2-6fe865037b01");
                	message.setStringProperty("MULE_ENDPOINT", "jms://feeds/out/1.0/price/contract");
                	message.setStringProperty("MULE_ROOT_MESSAGE_ID", "b95c0da0-33e3-11e7-9de8-f45c89c55fe1");
                	message.setStringProperty("MULE_SESSION", "rO0ABXNyACNvcmcubXVsZS5zZXNzaW9uLkRlZmF1bHRNdWxlU2Vzc2lvbi7rdtEW7GGKAwAFWgAFdmFsaWRMAA1mbG93Q29uc3RydWN0dAAmTG9yZy9tdWxlL2FwaS9jb25zdHJ1Y3QvRmxvd0NvbnN0cnVjdDtMAAJpZHQAEkxqYXZhL2xhbmcvU3RyaW5nO0wACnByb3BlcnRpZXN0AA9MamF2YS91dGlsL01hcDtMAA9zZWN1cml0eUNvbnRleHR0ACdMb3JnL211bGUvYXBpL3NlY3VyaXR5L1NlY3VyaXR5Q29udGV4dDt4cAFwdAAkYjk1Y2Y4MDAtMzNlMy0xMWU3LTlkZTgtZjQ1Yzg5YzU1ZmUxc3IAJWphdmEudXRpbC5Db2xsZWN0aW9ucyRTeW5jaHJvbml6ZWRNYXAbc");
                	message.setBooleanProperty("REQUIRES_RESEQUENCING", false);
                    return message;
                  }
                });
            System.out.println("Sending message " + i + " ##############################");
        }        
    }

}