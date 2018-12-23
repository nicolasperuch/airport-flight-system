package dev.peruch.createflight.api;

import dev.peruch.createflight.api.dto.CreateFlightDto;
import dev.peruch.createflight.service.CreateFlightService;
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

    @PostMapping("/flight")
    public ResponseEntity createFlight(@RequestBody CreateFlightDto createFlightDto){
        createFlightDto.setStatus("PENDING");
        createFlightService.feedAirportExchange(createFlightDto);
        return ok(createFlightDto.toString());
    }
}
