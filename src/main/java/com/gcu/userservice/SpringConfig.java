package com.gcu.userservice;

import com.gcu.userservice.business.UserBusinessInterface;
import com.gcu.userservice.business.UserBusinessService;
import com.gcu.userservice.data.DataAccessInterface;
import com.gcu.userservice.data.UserDataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean(name = "userBusinessService")
    public UserBusinessInterface getUsersBusiness(){
        return new UserBusinessService();
    }

    @Bean(name = "userDataService")
    public DataAccessInterface getUserDataService(){
        return new UserDataService();
    }
}
