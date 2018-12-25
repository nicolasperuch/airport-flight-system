package dev.peruch.queryflight.config;

import dev.peruch.queryflight.service.client.ElasticsearchClient;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig extends ElasticsearchConfig{

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        return Feign
                .builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(ElasticsearchClient.class, buildElasticsearchUri());
    }
}
