package dev.peruch.validateflight.service;

import dev.peruch.validateflight.model.CreateFlightModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static dev.peruch.validateflight.service.ConverterService.buildCreateFlightDto;

@Service
public class FlowService {

    @Autowired
    private ValidateFlightService validateFlightService;
    @Autowired
    private RabbitService rabbitService;
    private Logger logger = LoggerFactory.getLogger(FlowService.class);

    public void startFlow(Message message) {
        CreateFlightModel createFlightModel = buildCreateFlightDto(message);
        logger.info("Validating event");
        logger.info("model: " + createFlightModel.toString());
        createFlightModel = isValidFlight(createFlightModel) ?
                updateFlightStatus(createFlightModel, "flight.valid") :
                updateFlightStatus(createFlightModel, "flight.denied");
        logger.info("Event was send to: " + createFlightModel.getStatus());
        feedExchange(createFlightModel);
    }

    private boolean isValidFlight(CreateFlightModel createFlightModel){
        return validateFlightService.validateFlightRequest(createFlightModel);
    }

    public CreateFlightModel updateFlightStatus(CreateFlightModel createFlightModel, String status) {
        createFlightModel.setStatus(status);
        return createFlightModel;
    }

    private void feedExchange(CreateFlightModel createFlightModel) {
        rabbitService.feedExchange(createFlightModel);
    }
}
