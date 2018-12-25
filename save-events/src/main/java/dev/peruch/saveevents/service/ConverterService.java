package dev.peruch.saveevents.service;

import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

@Service
public class ConverterService {

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
}
