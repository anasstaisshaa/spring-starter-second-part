package com.dmdev.spring.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
@EnableEnversRepositories(basePackages = "com.dmdev.spring")
public class AuditConfiguration {

    @Bean
    public AuditorAware<String> auditorAware(){
        return () -> Optional.of("Anastasiia");
    }
}
