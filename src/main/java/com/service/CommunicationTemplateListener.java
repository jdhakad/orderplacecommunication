package com.service;

import com.Utility;
import com.amqp.AmqpListener;
import com.amqp.AmqpSender;
import com.pojo.TemplateInput;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by deepak.dhakad on 1/14/18.
 */
public class CommunicationTemplateListener extends AmqpListener<TemplateInput>{

    @Autowired
    private RenderService renderService;

    @Autowired
    private AmqpSender amqpSender;

    @Autowired
    @Qualifier("routerExchange")
    private TopicExchange routerExchange;

    @Override
    public void apply(TemplateInput templateInput) {
        templateInput.getTemplateList().forEach(template ->  {
            renderService.render(template, templateInput.getTemplateCtx());
            amqpSender.send(routerExchange, "communication.router", Utility.serialize(template));
        });
    }

    @Override
    public Class<TemplateInput> getMessageType() {
        return TemplateInput.class;
    }
}
