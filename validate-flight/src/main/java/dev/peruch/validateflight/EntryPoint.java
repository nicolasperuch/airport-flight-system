package dev.peruch.validateflight;

import dev.peruch.validateflight.config.RabbitAirportConfig;
import dev.peruch.validateflight.service.FlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EntryPoint extends RabbitAirportConfig {

    @Autowired
    private FlowService flowService;
    private Logger logger = LoggerFactory.getLogger(EntryPoint.class);

    @RabbitListener(queues = {CREATE_FLIGHT_QUEUE})
    public void receive(@Payload Message message) {
        logger.info("Event received: " + message.toString());
        flowService.startFlow(message);
    }
}
