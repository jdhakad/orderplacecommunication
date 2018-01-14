package com.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * Created by deepak.dhakad on 1/12/18.
 */
@Service
public class FreeMarkerRenderingService  {

    private Configuration cfg;

    public FreeMarkerRenderingService(){
        this.cfg = new Configuration();
        this.cfg.setDefaultEncoding("UTF-8");
        this.cfg.setLocale(Locale.US);
        this.cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    private String render(String id, String body, Map<String, Object> properties) throws TemplateException {
        try {
            Template template = new Template(id, new StringReader(body), this.cfg);
            StringWriter stringWriter = new StringWriter();
            template.process(properties, stringWriter);
            return stringWriter.toString();
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public String render(String body, Map<String, Object> properties) throws TemplateException {
        return this.render(UUID.randomUUID().toString(), body, properties);
    }
}
