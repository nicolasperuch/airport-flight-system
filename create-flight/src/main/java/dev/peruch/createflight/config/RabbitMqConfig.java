package dev.peruch.createflight.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig extends RabbitAirportConfig{

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
        return BindingBuilder.bind(createFlightQueue()).to(topicExchange()).with(routingKeyToCreateFlightQueue);
    }

    @Bean
    public Binding bindindExchangeToCreateFlightDeadLetter() {
        return BindingBuilder.bind(createFlightDeadLetterQueue()).to(topicExchange()).with(routingKeyToDeadLetter);
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
