package dev.peruch.queryflight.service;

import com.google.gson.Gson;
import dev.peruch.queryflight.model.CreateFlightModel;
import eventstore.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConverterService {

    @Autowired
    private Gson gson;
    private Logger logger = LoggerFactory.getLogger(ConverterService.class);

    public CreateFlightModel eventToModel(Event event){
        logger.info("Starting conversion process (event to model)");
        String jsonBody = eventToJson(event);
        logger.info("json clean body: \n{}", jsonBody);
        CreateFlightModel createFlightModel = jsonToModel(jsonBody);
        logger.info("create flight model: {}", createFlightModel.toString());
        return createFlightModel;
    }

    public String eventToJson(Event event) {
        String jsonBody = event.data().data().toString();
        jsonBody = removePrefix(jsonBody);
        return removeSuffix(jsonBody);
    }

    public String removePrefix(String json) {
        return json.replace("Content(","");
    }

    public String removeSuffix(String json) {
        return json.replace(",ContentType.Json)", "");
    }

    public CreateFlightModel jsonToModel(String json) {
        return gson.fromJson(json, CreateFlightModel.class);
    }
}
