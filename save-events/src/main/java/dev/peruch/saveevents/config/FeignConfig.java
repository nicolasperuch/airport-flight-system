package dev.peruch.saveevents.config;

import dev.peruch.saveevents.service.client.EventStoreClient;
import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig extends EventStoreConfig {

    @Bean
    public EventStoreClient write() {
        return Feign
                .builder()
                .target(EventStoreClient.class, buildEventStoreUri());
    }
}
