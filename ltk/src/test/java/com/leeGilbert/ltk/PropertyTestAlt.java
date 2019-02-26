package com.leeGilbert.ltk;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PropertyTestAlt {
    @Autowired
    private Analytics analytics;

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withInitializer(new ConfigFileApplicationContextInitializer())
            ;

    @Test
    public void testAnalyticsConfigAlt() throws Exception {

        this.contextRunner
                .withUserConfiguration(Analytics.class)
                .run((context) -> {
                    assertThat(analytics).isNotNull();
                    assertThat(analytics.getLogstashURL()).isNotNull();
                    assertThat(analytics.getLogstashURL())
                            .as("analytics.getLogstashURL() should match")
                            .isEqualToIgnoringCase("http://log:31311");
                });
    }

}
