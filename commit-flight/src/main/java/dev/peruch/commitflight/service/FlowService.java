package dev.peruch.commitflight.service;

import dev.peruch.commitflight.model.CreateFlightModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import static dev.peruch.commitflight.service.ConverterService.buildCreateFlightModel;

@Service
public class FlowService {

    private Logger logger = LoggerFactory.getLogger(FlowService.class);

    public void startFlow(Message message) {
        CreateFlightModel createFlightModel = buildCreateFlightModel(message);
        logger.info(createFlightModel.toString());
    }
}
