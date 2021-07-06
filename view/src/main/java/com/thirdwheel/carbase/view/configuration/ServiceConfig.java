package com.thirdwheel.carbase.view.configuration;

import com.thirdwheel.carbase.dao.models.*;
import com.thirdwheel.carbase.dao.repositories.GeneralRepository;
import com.thirdwheel.carbase.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ServiceConfig {
    private final GeneralRepository<Vendor> vendorRepository;
    private final GeneralRepository<Generation> generationRepository;
    private final GeneralRepository<Suspension> suspensionRepository;
    private final GeneralRepository<Chassis> chassisRepository;
    private final GeneralRepository<Engine> engineRepository;
    private final GeneralRepository<EngineModification> engineModificationRepository;
    private final GeneralRepository<Modification> modificationRepository;

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
