package dev.peruch.commitflight;

import dev.peruch.commitflight.config.RabbitAirportConfig;
import dev.peruch.commitflight.service.FlowService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EntryPoint extends RabbitAirportConfig {

    @Autowired
    private FlowService flowService;

    @RabbitListener(queues = {VALID_FLIGHT_QUEUE})
    public void receive(@Payload Message message) {
        flowService.startFlow(message);
    }
}
