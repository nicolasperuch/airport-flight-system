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
    @Value("${spring.rabbitmq.routing-key-to-valid-flight-queue}")
    protected String routingKeyToValidFlightQueue;
    @Value("${spring.rabbitmq.denied-flight-queue}")
    protected String deniedFlightQueue;
    @Value("${spring.rabbitmq.routing-key-to-denied-flight-queue}")
    protected String routingKeyToDeniedFlightQueue;
    @Value("${spring.rabbitmq.create-flight-dead-letter}")
    protected String createFlightDeadLetter;
    @Value("${spring.rabbitmq.routing-key-to-dead-letter}")
    protected String routingKeyToDeadLetter;
    protected static final String CREATE_FLIGHT_QUEUE = "create-flight";
}
