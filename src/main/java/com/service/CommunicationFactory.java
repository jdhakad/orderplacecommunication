package com.service;

import com.pojo.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import static com.pojo.Channel.EMAIL;

/**
 * Created by deepak.dhakad on 1/13/18.
 */
@Component
public class CommunicationFactory {

    @Autowired
    private EmailFactory emailFactory;

    @Autowired
    private SmsFactory smsFactory;

    public Sender<Template, ?> getSender(Channel channel) {
        switch (channel) {
            case EMAIL :
                return emailFactory.getEmailSender();
            case SMS:
                return smsFactory.getSmsSender();
        }

        return null;
    }
}
