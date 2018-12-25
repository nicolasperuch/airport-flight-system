package dev.peruch.saveevents.service.client;

import feign.Headers;
import feign.RequestLine;

public interface EventStoreClient {

    @RequestLine("POST /streams/basestream")
    @Headers("Content-Type: application/vnd.eventstore.events+json")
    String write(String content);
}
