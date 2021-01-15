package com.gcu.conversationservice;

import com.gcu.conversationservice.business.ConversationBusinessInterface;
import com.gcu.conversationservice.business.ConversationBusinessService;
import com.gcu.conversationservice.data.ConversationDataService;
import com.gcu.conversationservice.data.DataAccessInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean(name = "conversationBusinessService")
    public ConversationBusinessInterface getConversationBusiness(){
        return new ConversationBusinessService();
    }

    @Bean(name = "conversationDataService")
    public DataAccessInterface getConversationDataService(){
        return new ConversationDataService();
    }
}
