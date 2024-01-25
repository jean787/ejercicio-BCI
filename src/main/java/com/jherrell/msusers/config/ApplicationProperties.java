package com.jherrell.msusers.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
@Getter
@Setter
public class ApplicationProperties {

    @Value("${application.password.regexp}")
    private String passwordRgx;
}
