package com.thirdwheel.carbase.view.configuration;

import com.thirdwheel.carbase.dao.models.*;
import com.thirdwheel.carbase.dao.repositories.GeneralRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoriesConfig {
    @Bean
    public GeneralRepository<Vendor> vendorGeneralRepository(){
        return new GeneralRepository<>(Vendor.class);
    }
    @Bean
    public GeneralRepository<Suspension> suspensionGeneralRepository(){
        return new GeneralRepository<>(Suspension.class);
    }
    @Bean
    public GeneralRepository<Modification> modificationGeneralRepository(){
        return new GeneralRepository<>(Modification.class);
    }
    @Bean
    public GeneralRepository<Model> modelGeneralRepository(){
        return new GeneralRepository<>(Model.class);
    }
    @Bean
    public GeneralRepository<Generation> generationGeneralRepository(){
        return new GeneralRepository<>(Generation.class);
    }
    @Bean
    public GeneralRepository<EngineModification> engineModificationGeneralRepository(){
        return new GeneralRepository<>(EngineModification.class);
    }
    @Bean
    public GeneralRepository<Engine> engineGeneralRepository(){
        return new GeneralRepository<>(Engine.class);
    }
    @Bean
    public GeneralRepository<Chassis> chassisGeneralRepository(){
        return new GeneralRepository<>(Chassis.class);
    }
}
