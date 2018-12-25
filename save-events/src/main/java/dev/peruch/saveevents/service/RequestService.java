package dev.peruch.saveevents.service;

import dev.peruch.saveevents.service.client.EventStoreClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.UUID.randomUUID;

@Service
public class RequestService {

    @Autowired
    private EventStoreClient eventStoreClient;
    private Logger logger = LoggerFactory.getLogger(RequestService.class);

    public void writeIntoEventStore(String event) {
        logger.info("[EVENT STORE] - Starting writing process");
        eventStoreClient.write(event);
        logger.info("[EVENT STORE] - Update successfully made");
    }

    public String buildEvent(String jsonBody) {
        String event = buildEventContent(jsonBody);
        logger.info("[EVENT STORE] - Event message: \n{}", event);
        return event;
    }

    public String buildEventContent(String jsonBody){
        return  "[\n" +
                "  {\n" +
                "    \"eventId\": \""+ randomUUID().toString() + "\",\n" +
                "    \"eventType\": \"test\",\n" +
                "    \"data\": " + jsonBody +
                "  }\n" +
                "]";
    }
}
