package com.almod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value={"classpath:resources.properties"})
public class MultifunctionalProject {

    public static void main(String[] args) {
        SpringApplication.run(MultifunctionalProject.class, args);
    }

}
