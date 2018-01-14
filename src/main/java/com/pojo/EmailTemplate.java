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
public class EmailTemplate extends Template{
    private String body;
    private String header;

    EmailTemplate() {
        this.channel = Channel.EMAIL;
    }

    @Builder
    EmailTemplate(String body, String header) {
        this.channel = Channel.EMAIL;
        this.body = body;
        this.header = header;
    }
}
