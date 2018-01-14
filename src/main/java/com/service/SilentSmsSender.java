package com.service;

import com.pojo.SmsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by deepak.dhakad on 1/13/18.
 */
@Slf4j
@Component
public class SilentSmsSender extends Sender<Template, Template>{
    @Override
    public Template send(Template template) {
        SmsTemplate smsTemplate = (SmsTemplate) template;
        log.info("SMS sent with body: {}", smsTemplate.getBody());
        return template;
    }
}
