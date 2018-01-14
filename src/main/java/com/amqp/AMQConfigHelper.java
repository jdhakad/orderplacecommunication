package com.amqp;

import com.amqp.AmqpListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Configuration;

/**
 * Created by deepak.dhakad on 1/12/18.
 */
@Configuration
public class AMQConfigHelper {

    public static SimpleMessageListenerContainer containerWithProperties(String queueName,
                                                                         AmqpListener listener,
                                                                         ConnectionFactory connectionFactory,
                                                                         int concurrentConsumers) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(new MessageListenerAdapter(listener));
        container.setErrorHandler(Throwable::printStackTrace);
        container.setConcurrentConsumers(concurrentConsumers);
        return container;
    }

}
