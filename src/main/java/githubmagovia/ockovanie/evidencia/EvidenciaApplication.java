package githubmagovia.ockovanie.evidencia;

import githubmagovia.ockovanie.evidencia.rabbitmq.MessageSubscriber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class EvidenciaApplication {

	public static void main(String[] args) throws IOException, TimeoutException {
		SpringApplication.run(EvidenciaApplication.class, args);

		new MessageSubscriber().subscribe();
	}

}
