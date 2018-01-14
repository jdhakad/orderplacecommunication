package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by deepak.dhakad on 1/13/18.
 */
@Component
public class SmsFactory {

    @Autowired
    private SilentSmsSender silentSmsSender;

    public Sender<Template, ?> getSmsSender() {
        return silentSmsSender;
    }
}
