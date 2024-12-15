package com.app.exchange;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {
    @RabbitListener(queues = "myQueue")
    public void consumeMessage(String message) {
        System.out.println("Received: " + message);
    }
}
