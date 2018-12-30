package dev.peruch.commitflight.service;

import dev.peruch.commitflight.config.RabbitAirportConfig;
import dev.peruch.commitflight.model.CreateFlightModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static dev.peruch.commitflight.service.ConverterService.buildCreateFlightModel;

@Service
public class FlowService extends RabbitAirportConfig {

    @Autowired
    private RabbitService rabbitService;
    private Logger logger = LoggerFactory.getLogger(FlowService.class);

    public void startFlow(Message message) {
        CreateFlightModel createFlightModel = buildCreateFlightModel(message);
        createFlightModel = updateFlightStatus(createFlightModel, "flight.commit");
        logger.info(createFlightModel.toString());
        rabbitService.feedExchange(createFlightModel);
        logger.info("Event was send to: " + createFlightModel.getStatus());
    }

    public CreateFlightModel updateFlightStatus(CreateFlightModel createFlightModel, String status) {
        createFlightModel.setStatus(status);
        return createFlightModel;
    }
}
