package dev.peruch.commitflight.config;

import org.springframework.beans.factory.annotation.Value;

public abstract class RabbitAirportConfig {
    @Value("${spring.rabbitmq.host}")
    protected String host;
    @Value("${spring.rabbitmq.port}")
    protected Integer port;
    @Value("${spring.rabbitmq.username}")
    protected String username;
    @Value("${spring.rabbitmq.password}")
    protected String password;
    @Value("${spring.rabbitmq.exchange}")
    protected String exchange;
    @Value("${spring.rabbitmq.valid-flight-queue}")
    protected String validFlightQueue;
    @Value("${spring.rabbitmq.commit-flight-queue}")
    protected String commitFlightQueue;
    @Value("${spring.rabbitmq.routing-key-to-store-events-queue}")
    protected String routingKeyToCommitQueue;
    protected static final String VALID_FLIGHT_QUEUE = "valid-flight";
}
