package com.service;


import com.TemplateUtil;
import com.Utility;
import com.amqp.AmqpListener;
import com.amqp.AmqpSender;
import com.mapper.TemplateMapper;
import com.pojo.OrderPlacedBO;
import com.pojo.TemplateInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Map;

/**
 * Created by deepak.dhakad on 1/12/18.
 */
@Slf4j
public class OrderPlacedListener extends AmqpListener<OrderPlacedBO> {

    @Autowired
    private FreeMarkerRenderingService freeMarkerRenderingService;

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private AmqpSender amqpSender;

    @Autowired
    @Qualifier("templateExchange")
    private TopicExchange templateExchange;


    @Override
    @Async
    public void apply(OrderPlacedBO orderBO) {

        Map<String, Object> params = TemplateUtil.getParams(orderBO);
        Map<String, Object> templateCtx = TemplateUtil.getTemplateCtx(orderBO);
        List<Template> templates = templateMapper.getTemplates(templateCtx);
        amqpSender.send(templateExchange, "communication.template", Utility.serialize(TemplateInput.builder()
                .templateCtx(params)
                .templateList(templates)
                .build()));

    }



    @Override
    public Class<OrderPlacedBO> getMessageType() {
        return OrderPlacedBO.class;
    }

}
