package com.munro.api.config;

import com.munro.api.service.MunroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MunroConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            MunroService munroService
    ){
        return args -> {
            munroService.FetchMunros();
        };
    }
}
