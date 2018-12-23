package dev.peruch.createflight.api;

import dev.peruch.createflight.api.dto.CreateFlightDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class CreateFlightApi {

    @PostMapping("/flight")
    public ResponseEntity createFlight(@RequestBody CreateFlightDto createFlightDto){
        createFlightDto.setStatus("PENDING");

        return ok(createFlightDto.toString());
    }
}
