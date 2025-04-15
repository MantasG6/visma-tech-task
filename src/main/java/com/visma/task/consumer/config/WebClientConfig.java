package com.visma.task.consumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${thirdpartyservice.url}")
    private String URL;

    @Bean
    public WebClient webClient() {
        return WebClient
                .builder()
                .baseUrl(URL)
                .build();
    }

}
