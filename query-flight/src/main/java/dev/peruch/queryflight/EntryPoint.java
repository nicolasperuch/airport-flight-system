package dev.peruch.queryflight;

import akka.actor.ActorSystem;
import dev.peruch.queryflight.service.UpdateService;
import eventstore.Event;
import eventstore.SubscriptionObserver;
import eventstore.j.EsConnection;
import eventstore.j.EsConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Closeable;

@Component
public class EntryPoint {

    @Autowired
    private UpdateService updateService;
    private Logger logger = LoggerFactory.getLogger(EntryPoint.class);

    final ActorSystem system = ActorSystem.create();
    final EsConnection connection = EsConnectionFactory.create(system);
    final Closeable closeable = connection.subscribeToStream("basestream", new SubscriptionObserver<Event>() {
        @Override
        public void onLiveProcessingStart(Closeable subscription) {
            system.log().info("Live processing started");
        }

        @Override
        public void onEvent(Event event, Closeable subscription) {
            logger.info("Event received: \n {}", event.toString());
            updateService.startUpdateProcess(event);
        }

        @Override
        public void onError(Throwable e) {
            system.log().error(e.toString());
        }

        @Override
        public void onClose() {
            system.log().error("Subscription closed");
        }
    }, false, null);
}
