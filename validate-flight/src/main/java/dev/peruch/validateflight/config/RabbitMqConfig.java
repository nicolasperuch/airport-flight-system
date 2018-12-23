package dev.peruch.validateflight.config;

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
    public Queue validFlightQueue() {
        return new Queue(validFlightQueue, true, false, false);
    }

    @Bean
    public Queue deniedFlightQueue() {
        return new Queue(deniedFlightQueue, true, false, false);
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue(createFlightDeadLetter, true, false, false);
    }

    @Bean
    public Binding bindindExchangeToValidatedFlightQueue() {
        return BindingBuilder
                .bind(validFlightQueue())
                .to(topicExchange())
                .with(routingKeyToValidFlightQueue);
    }

    @Bean Binding bindingExchangeToDeniedFlightQueue() {
        return BindingBuilder
                .bind(deniedFlightQueue())
                .to(topicExchange())
                .with(routingKeyToDeniedFlightQueue);
    }

    @Bean
    public Binding bindindExchangeToDeadLetter() {
        return BindingBuilder
                .bind(deadLetterQueue())
                .to(topicExchange())
                .with(routingKeyToDeadLetter);
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
        rabbitAdmin.declareExchange(topicExchange());
        rabbitAdmin.declareQueue(validFlightQueue());
        rabbitAdmin.declareQueue(deniedFlightQueue());
        rabbitAdmin.declareQueue(deadLetterQueue());
        rabbitAdmin.declareBinding(bindindExchangeToValidatedFlightQueue());
        rabbitAdmin.declareBinding(bindingExchangeToDeniedFlightQueue());
        rabbitAdmin.declareBinding(bindindExchangeToDeadLetter());
        return rabbitAdmin;
    }
}
