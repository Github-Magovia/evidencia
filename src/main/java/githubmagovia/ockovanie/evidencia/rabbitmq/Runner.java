package githubmagovia.ockovanie.evidencia.rabbitmq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements ApplicationRunner {

    @Value("${message.exchange.name}")
    private String exchangeName;

    @Value("${worker.name}")
    private String name;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(name + " -> creating subscriber with exchange name: " + exchangeName);
        new MessageSubscriber(exchangeName, name).subscribe();
    }
}
