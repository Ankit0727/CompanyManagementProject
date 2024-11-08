package com.machinetest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "customAuditorAware") // Enables auditing and sets the auditor provider
public class AuditConfig {

    @Bean
    public CustomAuditorAware customAuditorAware() {
        return new CustomAuditorAware();
    }
}
