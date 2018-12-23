package dev.peruch.validateflight;

import dev.peruch.validateflight.config.RabbitAirportConfig;
import dev.peruch.validateflight.service.FlowService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EntryPoint extends RabbitAirportConfig {

    @Autowired
    private FlowService flowService;

    @RabbitListener(queues = {CREATE_FLIGHT_QUEUE})
    public void receive(@Payload Message message) {
        flowService.startFlow(message);
    }
}
