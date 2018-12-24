package dev.peruch.saveevents;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EntryPoint {

    @RabbitListener(queues = "flight-events")
    public void receive(@Payload Message message) {
        byte[] body = message.getBody();
        String jsonBody = new String(body);
        System.out.println(jsonBody);
    }
}
