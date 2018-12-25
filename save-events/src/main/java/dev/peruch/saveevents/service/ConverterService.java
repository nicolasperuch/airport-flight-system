package dev.peruch.saveevents.service;

import com.google.gson.Gson;
import dev.peruch.saveevents.model.CreateFlightModel;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConverterService {

    @Autowired
    private Gson gson;

    public String messageToJson(Message message){
        byte[] body = messageToBytes(message);
        return bytesToString(body);
    }

    public byte[] messageToBytes(Message message){
        return message.getBody();
    }

    public String bytesToString(byte[] body){
        return new String(body);
    }

    public CreateFlightModel jsonToModel(String json) {
        return gson.fromJson(json, CreateFlightModel.class);
    }
}
