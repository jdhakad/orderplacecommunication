package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by deepak.dhakad on 1/13/18.
 */
@Component
public class EmailFactory {

    @Autowired
    private SilentEmailSender silentEmailSender;

    public Sender<Template, ?> getEmailSender() {
        return silentEmailSender;
    }
}
