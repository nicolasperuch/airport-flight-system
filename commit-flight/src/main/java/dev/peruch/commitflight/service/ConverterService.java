package dev.peruch.commitflight.service;

import com.google.gson.Gson;
import dev.peruch.commitflight.model.CreateFlightModel;
import org.springframework.amqp.core.Message;

public class ConverterService {

    public static CreateFlightModel buildCreateFlightModel(Message message){
        byte[] body = transformMessageToBytes(message);
        String bodyAsJson = transformBytesToJson(body);
        return transformJsonToCreateFlightDto(bodyAsJson);
    }

    public static byte[] transformMessageToBytes(Message message){
        return message.getBody();
    }

    public static String transformBytesToJson(byte[] body){
        return new String(body);
    }

    public static CreateFlightModel transformJsonToCreateFlightDto(String jsonBody){
        return new Gson().fromJson(jsonBody, CreateFlightModel.class);
    }
}
