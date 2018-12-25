package dev.peruch.queryflight.service;

import dev.peruch.queryflight.model.CreateFlightModel;
import dev.peruch.queryflight.service.client.ElasticsearchClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElasticsearchService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public Object saveFlight(CreateFlightModel createFlightModel){
        return elasticsearchClient.createFlight(createFlightModel);
    }

}
