package com.abdelhadielomari.analyticsservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "jwt.secret=test-secret-test-secret-test-secret-123456",
        "spring.cloud.config.enabled=false",
        "eureka.client.enabled=false",
        "spring.kafka.consumer.group-id=test-group",
        "spring.kafka.listener.auto-startup=false"
})
class AnalyticsServiceApplicationTests {

    @Test
    void contextLoads() {
    }
}