package com.leegilbert.ltk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

/**
 * Posts data to logstash URL endpoint collector.
 * Enabled via config:
 * <pre>
 * analytics.enabled=true.
 * analytics.logstashURL=&lt;url:port&gt;.
 * </pre>
 *
 */
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
            .subscribe( // fixme {}
                    value -> log.debug("call returned {}", value),
                    error -> log.error("call errored {}",  error.getMessage()),
                    () -> log.warn("completed without a value")
            );
        }
    }

    public void log(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            logJson(mapper.writeValueAsString(o));
        } catch (Exception e) {
           log.error(e.getMessage(), e);
        }
    }


    @PostConstruct
    public void init() {
    }

}
