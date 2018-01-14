package com.service;

import com.amqp.AmqpListener;
import com.service.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by deepak.dhakad on 1/13/18.
 */
@Slf4j
public class CommunicationRouterListener extends AmqpListener<Template> {

    @Autowired
    private CommunicationFactory communicationFactory;

    @Override
    @Async
    public void apply(Template template) {
        communicationFactory.getSender(template.getChannel()).send(template, o -> {
            log.info("Communication working perfectly");
        }, ex -> {
            // we can track number of failures as well and retry count we can implement here
            log.error("Failure");
        });

    }

    @Override
    @Bean
    public Class<Template> getMessageType() {
        return Template.class;
    }
}
