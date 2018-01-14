package com.service;

import com.pojo.EmailTemplate;
import com.pojo.SmsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by deepak.dhakad on 1/13/18.
 */
@Service
@Slf4j
public class SilentEmailSender extends Sender<Template, Template>{

    @Override
    public Template send(Template template) {
        EmailTemplate emailTemplate = (EmailTemplate) template;
        log.info("Email sent with header: {}, body: {}", emailTemplate.getHeader(), emailTemplate.getBody());
        return template;
    }
}
