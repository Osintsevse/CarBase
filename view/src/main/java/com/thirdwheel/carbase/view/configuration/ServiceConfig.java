package com.thirdwheel.carbase.view.configuration;

import com.thirdwheel.carbase.dao.models.*;
import com.thirdwheel.carbase.dao.repositories.GeneralEntityWithNameRepository;
import com.thirdwheel.carbase.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ServiceConfig {
    private final GeneralEntityWithNameRepository<Vendor> vendorRepository;
    private final GeneralEntityWithNameRepository<Generation> generationRepository;
    private final GeneralEntityWithNameRepository<Suspension> suspensionRepository;
    private final GeneralEntityWithNameRepository<Chassis> chassisRepository;
    private final GeneralEntityWithNameRepository<Engine> engineRepository;
    private final GeneralEntityWithNameRepository<EngineModification> engineModificationRepository;
    private final GeneralEntityWithNameRepository<Modification> modificationRepository;

    @Bean
    public GeneralService<Vendor> vendorGeneralService() {
        return new GeneralService<>(vendorRepository);
    }

    @Bean
    public GeneralService<Generation> generationGeneralService() {
        return new GeneralService<>(generationRepository);
    }

    @Bean
    public GeneralService<Suspension> suspensionGeneralService() {
        return new GeneralService<>(suspensionRepository);
    }

    @Bean
    public GeneralService<Chassis> chassisGeneralService() {
        return new GeneralService<>(chassisRepository);
    }

    @Bean
    public GeneralService<Engine> engineGeneralService() {
        return new GeneralService<>(engineRepository);
    }

    @Bean
    public GeneralService<EngineModification> engineModificationGeneralService() {
        return new GeneralService<>(engineModificationRepository);
    }

    @Bean
    public GeneralService<Modification> modificationGeneralService() {
        return new GeneralService<>(modificationRepository);
    }
}
