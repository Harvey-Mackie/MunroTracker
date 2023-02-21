package com.munro.api.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigProperties {

    @Value("${app.refreshMunroDatabase}")
    public boolean refreshMunroDatabase;

    @Value("${app.munroCountCap}")
    public int munroCountCap;

    @Value("${app.weatherApiKey}")
    public String weatherApiKey;
}
