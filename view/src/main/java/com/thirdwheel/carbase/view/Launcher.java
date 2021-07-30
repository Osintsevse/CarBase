package com.thirdwheel.carbase.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan({
        "com.thirdwheel.carbase.view",
        "com.thirdwheel.carbase.service",
        "com.thirdwheel.carbase.dao"
})
public class Launcher {
    public static void main(String[] args) {
        SpringApplication.run(Launcher.class);
    }
}
