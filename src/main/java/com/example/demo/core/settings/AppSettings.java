package com.example.demo.core.settings;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Setter
@Getter
@Configuration
@ConfigurationProperties
public class AppSettings {
    private String name;
    private String environment;
    private String client;
}
