package com.example.invoicecreatorservice;

import com.example.invoicecreatorservice.contracts.services.StorageService;
import com.example.invoicecreatorservice.helpers.properties.StorageProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties(StorageProperties.class)
public class InvoiceCreatorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceCreatorServiceApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins(
                        "http://localhost:4200",
                        "http://localhost:4201",
                        "http://localhost:9090",
                        "http://localhost:9091"
                );
            }
        };
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return args -> storageService.init();
    }
}
