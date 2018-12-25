package dev.peruch.queryflight.service;

import dev.peruch.queryflight.config.AirportConfig;
import dev.peruch.queryflight.model.CreateFlightModel;
import eventstore.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateService extends AirportConfig {

    @Autowired
    private ConverterService converterService;
    @Autowired
    private ElasticsearchService elasticsearchService;
    private Logger logger = LoggerFactory.getLogger(UpdateService.class);

    public void startUpdateProcess(Event event){
        CreateFlightModel createFlightModel = converterService.eventToModel(event);
        if (isEventRelevant(createFlightModel)) {
            Object response = elasticsearchService.saveFlight(createFlightModel);
            //update database
            logger.info("event update into es and database!");
        } else {
            logger.info("event was not relevant to business :'(");
        }

    }

    public boolean isEventRelevant(CreateFlightModel createFlightModel){
        return isStatusCommit(createFlightModel);
    }

    public boolean isStatusCommit(CreateFlightModel createFlightModel){
        return createFlightModel.getStatus().equalsIgnoreCase(STATUS_COMMIT);
    }


}
