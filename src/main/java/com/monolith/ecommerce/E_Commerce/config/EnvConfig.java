package com.monolith.ecommerce.E_Commerce.config;

import me.paulschwarz.springdotenv.DotenvPropertySource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PropertySourceFactory;

@Configuration
@PropertySource(factory = PropertySourceFactory.class, value = "classpath:.env", ignoreResourceNotFound = true)
public class EnvConfig {
    // No static block needed
}
