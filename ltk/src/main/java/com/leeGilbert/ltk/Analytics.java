package com.leeGilbert.ltk;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;

@Component
@Data
@Validated
@Slf4j
public class Analytics {

    @NotBlank
    private String logstashURL;

    @NotNull
    private Boolean enabled;

    @NotNull
    private WebClient client = buildClient();


    public Analytics(@Value("${analytics.logstashURL}") String logstashURL, @Value("${analytics.enabled}") Boolean enabled) {
        this.logstashURL = logstashURL;
        this.enabled = enabled;
    }

    private WebClient buildClient() {
        return WebClient
                .builder()
                .baseUrl(logstashURL)
                //.defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", logstashURL))
                .build();
    }

    public void logJson(String json) {
        if (enabled) {
             client.post().body(BodyInserters.fromObject(json))
            .exchange()
            .subscribe(
                    value -> log.debug("call returned {}", value),
                    error -> log.error("call returned {}",  error.getMessage()),
                    () -> log.warn("completed without a value")
            );
        }
    }


    @PostConstruct
    public void init() {
    }

}
