package dev.peruch.commitflight;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class CommitFlightApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommitFlightApplication.class, args);
	}

}

