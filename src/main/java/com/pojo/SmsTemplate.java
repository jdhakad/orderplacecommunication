package com.pojo;

import com.service.Template;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by deepak.dhakad on 1/13/18.
 */
@Data
public class SmsTemplate extends Template{
    private String body;

//    @Builder
//    SmsTemplate(String body, Channel channel) {
//        super(channel);
//        this.body = body;
//    }

    SmsTemplate() {
        this.channel = Channel.SMS;
    }
    @Builder
    SmsTemplate(String body) {
        this.body = body;
        this.channel = Channel.SMS;
    }
}

