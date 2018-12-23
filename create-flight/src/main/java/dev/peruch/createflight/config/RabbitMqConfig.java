package dev.peruch.createflight.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private Integer port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.create-flight-queue}")
    private String createFlightQueue;
    @Value("${spring.rabbitmq.create-flight-dead-letter}")
    private String createFlightDeadLetter;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchange, true, false);
    }

    @Bean
    public Queue createFlightQueue() {
        return new Queue(createFlightQueue, true, false, false);
    }

    @Bean
    public Queue createFlightDeadLetterQueue() {
        return new Queue(createFlightDeadLetter, true, false, false);
    }

    @Bean
    public Binding bindindExchangeToCreateFlightQueue() {
        return BindingBuilder.bind(createFlightQueue()).to(topicExchange()).with(createFlightQueue);
    }

    @Bean
    public Binding bindindExchangeToCreateFlightDeadLetter() {
        return BindingBuilder.bind(createFlightDeadLetterQueue()).to(topicExchange()).with(createFlightDeadLetter);
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
        rabbitAdmin.declareExchange(topicExchange());
        rabbitAdmin.declareQueue(createFlightQueue());
        rabbitAdmin.declareQueue(createFlightDeadLetterQueue());
        rabbitAdmin.declareBinding(bindindExchangeToCreateFlightQueue());
        rabbitAdmin.declareBinding(bindindExchangeToCreateFlightDeadLetter());
        return rabbitAdmin;
    }
}
