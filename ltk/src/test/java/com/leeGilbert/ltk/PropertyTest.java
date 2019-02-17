package com.leeGilbert.ltk;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import org.springframework.test.context.junit4.SpringRunner;



import static org.hamcrest.core.IsNull.notNullValue;

@SpringBootTest
@TestPropertySource(properties = { "spring.config.location=classpath:application.yml" })
@ContextConfiguration(classes = { Analytics.class },  initializers = {ConfigFileApplicationContextInitializer.class})
@RunWith(SpringRunner.class)
@Slf4j
public class PropertyTest {

    @Autowired
    private Analytics analytics;

    @Test
    public void TestAnalytics() {
        Assert.assertThat("analytics not null " , analytics, notNullValue());
        Assert.assertThat("analytics.getEnabled() not null " , analytics.getEnabled(), notNullValue());
    }
}
