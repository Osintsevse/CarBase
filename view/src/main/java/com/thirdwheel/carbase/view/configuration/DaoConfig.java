package com.thirdwheel.carbase.view.configuration;

import com.thirdwheel.carbase.dao.repositories.VendorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {
    @Bean
    public VendorRepository getVendorRepository(){
        return new VendorRepository();
    }
}
