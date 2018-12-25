package dev.peruch.createflight.config;

import org.springframework.beans.factory.annotation.Value;

public abstract class RabbitAirportConfig {
    protected String PENDING_STATUS = "flight.pending";
    protected String routingKeyToCreateFlightQueue = PENDING_STATUS;
    protected String DEAD_LETTER_KEY = "flight.dead-letter";
    protected String routingKeyToDeadLetter = DEAD_LETTER_KEY;
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
    @Value("${spring.rabbitmq.create-flight-queue}")
    protected String createFlightQueue;
    @Value("${spring.rabbitmq.flight-dead-letter}")
    protected String createFlightDeadLetter;
}
