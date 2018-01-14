package com.amqp;

import com.service.CommunicationRouterListener;
import com.service.CommunicationTemplateListener;
import com.service.OrderPickedUpListener;
import com.service.OrderPlacedListener;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by deepak.dhakad on 1/12/18.
 */
@Configuration
public class AMQPConfig {

    private final int MAX_CONCURRENT_CONSUMERS = 50;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    ConnectionFactory connectionFactory;

    @Bean
    TopicExchange eventExchange() {
        return new TopicExchange("order.event");
    }

    @Bean
    TopicExchange routerExchange() { return new TopicExchange("communication.router");}

    @Bean
    TopicExchange templateExchange() { return new TopicExchange("communication.template");}


    @Bean
    public SimpleMessageListenerContainer placeOrderListenerConfig(){
        return AMQConfigHelper.containerWithProperties("order.placed",
                eventRabbitmqListener(),
                connectionFactory,
                MAX_CONCURRENT_CONSUMERS);
    }

    @Bean
    public SimpleMessageListenerContainer routingMessageListenerConfig() {
        return AMQConfigHelper.containerWithProperties("communication.router",
                communicationRouterListener(),
                connectionFactory,
                MAX_CONCURRENT_CONSUMERS);
    }

    @Bean
    public SimpleMessageListenerContainer templateListenerConfig() {
        return AMQConfigHelper.containerWithProperties("communication.template",
                communicationTemplateListener(),
                connectionFactory,
                MAX_CONCURRENT_CONSUMERS);
    }


    @Bean
    public SimpleMessageListenerContainer orderPickedUpListenerConfig() {
        return AMQConfigHelper.containerWithProperties("order.pickedup",
                orderPickedUpListener(),
                connectionFactory,
                MAX_CONCURRENT_CONSUMERS);
    }

    @Bean
    public OrderPickedUpListener orderPickedUpListener() {return new OrderPickedUpListener();}

    @Bean
    public CommunicationTemplateListener communicationTemplateListener() {return new CommunicationTemplateListener();}

    @Bean
    public CommunicationRouterListener communicationRouterListener() {return new CommunicationRouterListener(); }

    @Bean
    public OrderPlacedListener eventRabbitmqListener(){
        return new OrderPlacedListener();
    }


}
