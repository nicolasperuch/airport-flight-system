package dev.peruch.createflight.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class CreateFlightApi {

    @GetMapping
    public ResponseEntity createFlight(){
        return ok("OK");
    }
}
