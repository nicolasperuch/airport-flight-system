package dev.peruch.queryflight.config;

import org.springframework.beans.factory.annotation.Value;

public abstract class ElasticsearchConfig {
    @Value("${spring.data.elasticsearch.host}")
    protected String ELASTICSEARCH_HOST;
    @Value("${spring.data.elasticsearch.port}")
    protected String ELASTICSEARCH_PORT;

    protected String buildElasticsearchUri(){
        return ELASTICSEARCH_HOST + ":" + ELASTICSEARCH_PORT;
    }
}
