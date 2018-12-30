package dev.peruch.commitflight.service;

import com.google.gson.Gson;
import dev.peruch.commitflight.config.RabbitAirportConfig;
import dev.peruch.commitflight.model.CreateFlightModel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitService extends RabbitAirportConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Gson gson;

    public void feedExchange(CreateFlightModel createFlightModel) {
        rabbitTemplate.convertAndSend(exchange, createFlightModel.getStatus(), buildMessage(createFlightModel));
    }

    public Message buildMessage(CreateFlightModel createFlightModel){
        String bodyMessage = gson.toJson(createFlightModel);
        return new Message(bodyMessage.getBytes(), new MessageProperties());
    }
}
