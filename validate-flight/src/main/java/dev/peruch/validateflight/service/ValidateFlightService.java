package dev.peruch.validateflight.service;

import dev.peruch.validateflight.model.CreateFlightModel;
import org.springframework.stereotype.Service;

@Service
public class ValidateFlightService {

    public boolean validateFlightRequest(CreateFlightModel createFlightModel){
        return isPilotAvailable(createFlightModel.getPilot());
    }

    public boolean isPilotAvailable(String pilot){
        //validate pilot here, i will do some dumb logic for now XD
        return pilot.startsWith("Joseph");
    }
}
