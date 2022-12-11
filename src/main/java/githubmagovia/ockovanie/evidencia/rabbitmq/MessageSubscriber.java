package githubmagovia.ockovanie.evidencia.rabbitmq;

import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class MessageSubscriber {

    private int counter  = 0;

    @Value("${message.exchange.name}")
    private String exchangeName;

    @Value("${worker.name}")
    private String name;

    public void subscribe() throws IOException, TimeoutException {
        System.out.println(name + " -> connecting to channel with exchange name: " + exchangeName);
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("rabbitmq");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT);
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, exchangeName, "");

        System.out.println(name + " -> Waiting for messages...");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(name + " -> Received published message #" + counter++ +": " + message);
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }
}
