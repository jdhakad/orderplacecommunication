package com.service;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pojo.Channel;
import com.pojo.EmailTemplate;
import com.pojo.SmsTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by deepak.dhakad on 1/13/18.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "channel")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SmsTemplate.class, name = "SMS"),
        @JsonSubTypes.Type(value = EmailTemplate.class, name = "EMAIL")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Template {
    protected Channel channel;
}
