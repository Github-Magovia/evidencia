package githubmagovia.ockovanie.evidencia.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class EmailListener {

    @RabbitListener(queues = "emailQueue")
    public void listen(String body) {
        System.out.printf("Sending mail with body:%n%s%n", body);
    }
}
