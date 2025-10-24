package com.example.TechLearningHub.config;

//import lombok.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class QiitaApiConfig {

    @Value("${spring.qiita.api.base-url}")
    private String baseUrl;

    @Value("${spring.qiita.api.access-token}")
    private String accessToken;

    //Qiita API用のRestTemplateを構成
    @Bean
    public RestTemplate qiitaRestTemplate(RestTemplateBuilder builder) {
        RestTemplateBuilder configuredBuilder = builder
                .rootUri(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        if(accessToken != null && !accessToken.isEmpty()) {
            configuredBuilder = configuredBuilder
                    .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        }

        return configuredBuilder.build();
    }

}
