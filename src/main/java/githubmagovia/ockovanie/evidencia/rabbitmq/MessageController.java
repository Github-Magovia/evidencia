package githubmagovia.ockovanie.evidencia.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
@Slf4j
public class MessageController {

    @Value("${email.queue.name}")
    private String queueName;

    private final MessagePublisher publisher;

    private final RabbitTemplate rabbitTemplate;

    public MessageController(MessagePublisher publisher, RabbitTemplate rabbitTemplate) {
        this.publisher = publisher;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/send-to-worker")
    public void send(@RequestBody String message) {
        System.out.println("Publisher -> /send-to-worker with message: " + message);

        rabbitTemplate.convertAndSend(queueName, message);
    }

    @PostMapping("/publish")
    public void sendReply(@RequestBody String name) throws Exception {
        System.out.println("Publisher -> /publish with message: " + name);

        publisher.sendMessage(name);
    }
}
