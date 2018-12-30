package dev.peruch.createflight.api;

import dev.peruch.createflight.api.dto.CreateFlightDto;
import dev.peruch.createflight.service.CreateFlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class CreateFlightApi {

    @Autowired
    private CreateFlightService createFlightService;
    private Logger logger = LoggerFactory.getLogger(CreateFlightApi.class);

    @PostMapping("/flight")
    public ResponseEntity createFlight(@RequestBody CreateFlightDto createFlightDto){
        logger.info("Received: " + createFlightDto.toString());
        createFlightService.feedAirportExchange(createFlightDto);
        return ok(createFlightDto.toString());
    }
}
