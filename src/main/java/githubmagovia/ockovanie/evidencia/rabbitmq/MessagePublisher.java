package githubmagovia.ockovanie.evidencia.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Component
public class MessagePublisher {

    @Value("${message.exchange.name}")
    private String exchangeName;

    public void sendMessage(String message) throws IOException, TimeoutException {
        System.out.println("Publisher -> channel with exchange name: " + exchangeName);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("rabbitmq");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT);

            channel.basicPublish(exchangeName,
                    "",
                    null,
                    message.getBytes(StandardCharsets.UTF_8));
            System.out.println("Publisher -> sent message: " + message);
        }
    }
}
