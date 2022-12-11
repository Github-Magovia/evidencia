package githubmagovia.ockovanie.evidencia.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Value("${email.queue.name}")
    private String queueName;

    @Bean
    public Queue emailQueue() {
        return new Queue(queueName, false);
    }
}
