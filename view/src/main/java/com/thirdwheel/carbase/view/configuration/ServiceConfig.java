package com.thirdwheel.carbase.view.configuration;

import com.thirdwheel.carbase.dao.models.Vendor;
import com.thirdwheel.carbase.dao.repositories.GeneralEntityWithNameRepository;
import com.thirdwheel.carbase.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ServiceConfig {
    private final GeneralEntityWithNameRepository<Vendor> vendorRepository;

    @Bean
    public GeneralService<Vendor, GeneralEntityWithNameRepository<Vendor>> vendorGeneralService() {
        return new GeneralService<>(vendorRepository);
    }

}
