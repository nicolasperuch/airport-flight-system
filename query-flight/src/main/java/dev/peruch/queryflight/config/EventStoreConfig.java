package dev.peruch.queryflight.config;

import akka.actor.ActorSystem;
import eventstore.Event;
import eventstore.SubscriptionObserver;
import eventstore.j.EsConnection;
import eventstore.j.EsConnectionFactory;
import org.springframework.context.annotation.Configuration;

import java.io.Closeable;

@Configuration
public class EventStoreConfig {
    final ActorSystem system = ActorSystem.create();
    final EsConnection connection = EsConnectionFactory.create(system);
    final Closeable closeable = connection.subscribeToStream("basestream", new SubscriptionObserver<Event>() {
        @Override
        public void onLiveProcessingStart(Closeable subscription) {
            system.log().info("live processing started");
        }

        @Override
        public void onEvent(Event event, Closeable subscription) {
            system.log().info(event.toString());
        }

        @Override
        public void onError(Throwable e) {
            system.log().error(e.toString());
        }

        @Override
        public void onClose() {
            system.log().error("subscription closed");
        }
    }, false, null);
}
