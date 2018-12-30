package dev.peruch.createflight.service;

import com.google.gson.Gson;
import dev.peruch.createflight.api.dto.CreateFlightDto;
import dev.peruch.createflight.config.RabbitAirportConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(CreateFlightService.class);

    public void feedAirportExchange(CreateFlightDto createFlightDto){
        logger.info("Sending event to rabbit");
        rabbitTemplate.convertAndSend(exchange, routingKeyToCreateFlightQueue, buildMessage(createFlightDto));
        logger.info("Event was send to: " + routingKeyToCreateFlightQueue);
    }

    public Message buildMessage(CreateFlightDto createFlightDto){
        createFlightDto = setPendingStatus(createFlightDto);
        String bodyMessage = gson.toJson(createFlightDto);
        return new Message(bodyMessage.getBytes(), new MessageProperties());
    }

    public CreateFlightDto setPendingStatus(CreateFlightDto createFlightDto){
        return createFlightDto.withStatus(PENDING_STATUS);
    }
}
