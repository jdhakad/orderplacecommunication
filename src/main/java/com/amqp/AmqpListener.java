package com.amqp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

/**
 * Created by deepak.dhakad on 1/12/18.
 */
@Component
@Slf4j
public abstract class AmqpListener<T> {


    private ObjectMapper objectMapper = new ObjectMapper();

    @RabbitHandler
    public void receive(byte[] message){
        try {
            apply(objectMapper.readValue(message, getMessageType()));
        }catch (Exception e){
            log.error("Error in evaluating message: {} {}", String.valueOf(message), e);
        }
    }

    @RabbitHandler
    public void receive(String message){
        try {
            if(!getMessageType().equals(String.class))
                apply(Utility.deserialize(message, getMessageType()));
            else
                apply((T) message);
        }catch (Exception e){
            log.error("Error in evaluating message: {} {}", String.valueOf(message), e);
        }
    }

    public void handleMessage(byte[] payload){
        receive(payload);
    }

    public void handleMessage(String payload) {
        receive(payload);
    }

    public abstract void apply(T t);

    public abstract Class<T> getMessageType();
}