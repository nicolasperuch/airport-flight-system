package dev.peruch.queryflight.service.client;

import dev.peruch.queryflight.model.CreateFlightModel;
import feign.Headers;
import feign.RequestLine;

public interface ElasticsearchClient {

    @RequestLine("GET /airport-flight")
    String getIndexInfo();

    @RequestLine("POST /airport-flight/flight")
    @Headers("Content-Type: application/json")
    Object createFlight(CreateFlightModel createFlightModel);
}
