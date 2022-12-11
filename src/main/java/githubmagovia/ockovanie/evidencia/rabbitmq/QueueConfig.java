package githubmagovia.ockovanie.evidencia.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Value("${email.queue.name}")
    private String queueName;

    @Value("${worker.name}")
    private String name;

    @Bean
    public Queue emailQueue() {
        System.out.println(name + " -> creating queue with name: " + queueName);
        return new Queue(queueName, false);
    }
}
