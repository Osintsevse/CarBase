package com.thirdwheel.carbase.view.configuration;

import com.thirdwheel.carbase.dao.models.*;
import com.thirdwheel.carbase.dao.repositories.GeneralEntityWithNameRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoriesConfig {
    @Bean
    public GeneralEntityWithNameRepository<Vendor> vendorGeneralRepository() {
        return new GeneralEntityWithNameRepository<>(Vendor.class);
    }

    @Bean
    public GeneralEntityWithNameRepository<Suspension> suspensionGeneralRepository() {
        return new GeneralEntityWithNameRepository<>(Suspension.class);
    }

    @Bean
    public GeneralEntityWithNameRepository<Generation> generationGeneralRepository() {
        return new GeneralEntityWithNameRepository<>(Generation.class);
    }

    @Bean
    public GeneralEntityWithNameRepository<EngineModification> engineModificationGeneralRepository() {
        return new GeneralEntityWithNameRepository<>(EngineModification.class);
    }

    @Bean
    public GeneralEntityWithNameRepository<Engine> engineGeneralRepository() {
        return new GeneralEntityWithNameRepository<>(Engine.class);
    }

    @Bean
    public GeneralEntityWithNameRepository<Chassis> chassisGeneralRepository() {
        return new GeneralEntityWithNameRepository<>(Chassis.class);
    }
}
