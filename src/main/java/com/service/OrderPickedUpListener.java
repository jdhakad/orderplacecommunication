package com.service;

import com.TemplateUtil;
import com.Utility;
import com.amqp.AmqpListener;
import com.amqp.AmqpSender;
import com.mapper.TemplateMapper;
import com.pojo.BaseRequest;
import com.pojo.TemplateInput;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Map;

/**
 * Created by deepak.dhakad on 1/14/18.
 */
public class OrderPickedUpListener extends AmqpListener<BaseRequest> {

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
    public void apply(BaseRequest baseRequest) {

        Map<String, Object> params = TemplateUtil.getParams(baseRequest);
        Map<String, Object> templateCtx = TemplateUtil.getTemplateCtx(baseRequest);
        List<Template> templates = templateMapper.getTemplates(templateCtx);
        amqpSender.send(templateExchange, "communication.template", Utility.serialize(TemplateInput.builder()
                .templateCtx(params)
                .templateList(templates)
                .build()));
    }

    @Override
    public Class<BaseRequest> getMessageType() {
        return BaseRequest.class;
    }
}
