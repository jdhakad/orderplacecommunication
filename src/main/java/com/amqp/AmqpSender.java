package com.amqp;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * Created by deepak.dhakad on 1/12/18.
 */
@Component
public class AmqpSender {

    @Autowired
    private RabbitTemplate template;

    public Consumer<Object> getConsumer(Queue queue){
        return o -> send(queue, o);
    }

    public void send(Queue queue, Object message) {
        this.template.convertAndSend(queue.getName(), message);
    }

    public void send(Exchange exchange, String routingKey, Object message) {
        this.template.convertAndSend(exchange.getName(), routingKey, message);
    }
}
