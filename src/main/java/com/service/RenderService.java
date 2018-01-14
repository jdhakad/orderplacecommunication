package com.service;

import com.pojo.EmailTemplate;
import com.pojo.SmsTemplate;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by deepak.dhakad on 1/14/18.
 */
@Service
@Slf4j
public class RenderService {

    @Autowired
    private FreeMarkerRenderingService freeMarkerRenderingService;

    public void render(Template template, Map<String, Object> params) {
        try {
            switch (template.getChannel()) {
                case EMAIL:
                    EmailTemplate emailTemplate = (EmailTemplate) template;
                    emailTemplate.setHeader(freeMarkerRenderingService.render(emailTemplate.getHeader(), params));
                    emailTemplate.setBody(freeMarkerRenderingService.render(emailTemplate.getBody(), params));
                    break;
                case SMS:
                    SmsTemplate smsTemplate = (SmsTemplate) template;
                    smsTemplate.setBody(freeMarkerRenderingService.render(smsTemplate.getBody(), params));
            }
        } catch (TemplateException e) {
            log.error("Template Error: {}", e);
        }
    }
}
