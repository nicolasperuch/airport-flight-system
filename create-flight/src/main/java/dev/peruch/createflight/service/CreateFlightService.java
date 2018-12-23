package dev.peruch.createflight.service;

import com.google.gson.Gson;
import dev.peruch.createflight.api.dto.CreateFlightDto;
import dev.peruch.createflight.config.RabbitAirportConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateFlightService extends RabbitAirportConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Gson gson;

    public void feedAirportExchange(CreateFlightDto createFlightDto){
        rabbitTemplate.convertAndSend(exchange, createFlightQueue, buildMessage(createFlightDto));
    }

    public Message buildMessage(CreateFlightDto createFlightDto){
        String bodyMessage = gson.toJson(createFlightDto);
        return new Message(bodyMessage.getBytes(), new MessageProperties());
    }
}
