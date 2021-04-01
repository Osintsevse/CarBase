package com.thirdwheel.carbase.view;


import com.thirdwheel.carbase.view.configuration.JpaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication()
@Import(JpaConfig.class)
public class Launcher {
    public static void main(String[] args) {
        SpringApplication.run(Launcher.class);
    }
}
