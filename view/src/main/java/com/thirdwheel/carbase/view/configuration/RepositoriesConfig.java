package com.thirdwheel.carbase.view.configuration;

import com.thirdwheel.carbase.dao.models.Vendor;
import com.thirdwheel.carbase.dao.repositories.GeneralEntityWithNameRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoriesConfig {
    @Bean
    public GeneralEntityWithNameRepository<Vendor> vendorGeneralRepository() {
        return new GeneralEntityWithNameRepository<>(Vendor.class);
    }
}
