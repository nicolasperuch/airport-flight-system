package dev.peruch.queryflight.model;

import java.util.StringJoiner;

public class CreateFlightModel {
    private String origin;
    private String destination;
    private String airplaneModel;
    private String pilot;
    private String status;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAirplaneModel() {
        return airplaneModel;
    }

    public void setAirplaneModel(String airplaneModel) {
        this.airplaneModel = airplaneModel;
    }

    public String getPilot() {
        return pilot;
    }

    public void setPilot(String pilot) {
        this.pilot = pilot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CreateFlightModel.class.getSimpleName() + "[", "]")
                .add("origin='" + origin + "'")
                .add("destination='" + destination + "'")
                .add("airplaneModel='" + airplaneModel + "'")
                .add("pilot='" + pilot + "'")
                .add("status='" + status + "'")
                .toString();
    }
}
