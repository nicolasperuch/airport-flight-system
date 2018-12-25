package dev.peruch.saveevents;

import dev.peruch.saveevents.service.ConverterService;
import dev.peruch.saveevents.service.RequestService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EntryPoint {

    @Autowired
    private RequestService eventStoreService;
    @Autowired
    private ConverterService converterService;

    @RabbitListener(queues = "flight-events")
    public void receive(@Payload Message message) {
        String jsonBody = converterService.messageToJson(message);
        String event = eventStoreService.buildEvent(jsonBody);
        eventStoreService.writeIntoEventStore(event);
    }
}
