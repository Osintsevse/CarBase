package com.thirdwheel.carbase.view.configuration;

import com.thirdwheel.carbase.dao.models.Vendor;
import com.thirdwheel.carbase.dao.repositories.GeneralRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoriesConfig {
    @Bean
    public GeneralRepository<Vendor> vendorGeneralRepository(){
        return new GeneralRepository<>(Vendor.class);
    }
}
