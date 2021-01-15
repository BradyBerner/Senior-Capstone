package com.gcu.MessageService;

import com.gcu.MessageService.business.MessageBusinessInterface;
import com.gcu.MessageService.business.MessageBusinessService;
import com.gcu.MessageService.data.DataAccessInterface;
import com.gcu.MessageService.data.MessageDataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean(name = "messageBusinessService")
    public MessageBusinessInterface getMessageBusinessService(){
        return new MessageBusinessService();
    }

    @Bean(name = "messageDataService")
    public DataAccessInterface getMessageDataService(){
        return new MessageDataService();
    }
}
