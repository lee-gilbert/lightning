package com.leeGilbert.ltk;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "analytics")
public class AnalyticsProps {
    String logstashURL;
}
