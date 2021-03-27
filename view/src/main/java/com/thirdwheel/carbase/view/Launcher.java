package com.thirdwheel.carbase.view;


import com.thirdwheel.carbase.view.configuration.DaoConfig;
import com.thirdwheel.carbase.view.configuration.JpaConfig;
import com.thirdwheel.carbase.view.configuration.ServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication()
@Import({DaoConfig.class, JpaConfig.class, ServiceConfig.class})
public class Launcher {
    public static void main(String[] args) {
        SpringApplication.run(Launcher.class);
    }
}
