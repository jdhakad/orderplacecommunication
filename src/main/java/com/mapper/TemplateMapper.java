package com.mapper;

import com.pojo.Channel;
import com.pojo.EmailTemplate;
import com.pojo.EventType;
import com.pojo.SmsTemplate;
import com.service.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by deepak.dhakad on 1/13/18.
 */
@Component
@Slf4j
public class TemplateMapper {

    private String SMS_BODY_WITH_INVOICE = "Your ${companyName} order #${orderId} is placed successfully. Your order total is ${invoiceDetails.orderTotal}.";

    private String SMS_BODY_WITHOUT_INVOICE = "Your ${companyName} order #${orderId} is placed successfully. Your invoice will be generated shortly.";

    private String EMAIL_HEADER= "Your ${companyName} order #${orderId} is placed successfully";

    private String EMAIL_BODY_WITH_INVOICE = "<html>" +
            "Hello ${userName},<br/><br/>" +
            "&nbsp;&nbsp;&nbsp;&nbsp; We would like to inform you that your order #${orderId} is  placed on ${companyName}. You will receive your order shortly.<br/><br/>\n" +
            "" +
            "Please find the invoice details below:" +
            "<table border=\"1\">" +
            "        <tr><th>Item Name</th><th>Quantity</th><th>Price</th></tr>" +
            "<#list 0..invoiceDetails.itemList?size-1 as i>" +
            "<tr>" +
            "<td>${invoiceDetails.itemList[i].name}</td>" +
            "<td>${invoiceDetails.itemList[i].quantity}</td>" +
            "<td>${invoiceDetails.itemList[i].price}</td>" +
            "</tr>" +
            "</#list>" +
            "<tr><td colspan=\"2\" align=\"right\">Delivery Charge:</td><td>${invoiceDetails.deliveryCharge}</td></tr>" +
            "<tr><td colspan=\"2\" align=\"right\">Tax:</td><td>${invoiceDetails.tax}</td></tr>" +
            "<tr><td colspan=\"2\" align=\"right\">Order Total:</td><td>${invoiceDetails.orderTotal}</td></tr>" +
            "</table>" +
            "" +
            "Thank you for ordering.<br/><br/>" +
            "${companyName}\n" +
            "</html>";


    private String EMAIL_BODY_WITHOUT_INVOICE = "Hello dhdakad,<br/><br/>" +
            "&nbsp;&nbsp;&nbsp;&nbsp; We would like to inform you that your order #23424 is  placed on Tangy Street. You will receive your order shortly.<br/><br/>\n" +
            "" +
            "We will send invoice soon.<br/><br/>" +
            "" +
            "Thank you for ordering.<br/>" +
            "Tangy Street" +
            "</html>";

    public List<Template> getTemplates(Map<String, Object> ctx) {
        EventType eventType = EventType.valueOf(String.valueOf(ctx.get("EVENT_TYPE")));
        switch (eventType) {
            case ORDER_PLACED:
                boolean invoicePresent = Boolean.parseBoolean(ctx.get("INVOICE_PRESENT").toString());
                if (invoicePresent) {
                    return Arrays.asList(
                            EmailTemplate.builder()
                                    .body(EMAIL_BODY_WITH_INVOICE)
                                    .header(EMAIL_HEADER)
                                    .build(),
                            SmsTemplate.builder()
                                    .body(SMS_BODY_WITH_INVOICE)
                                    .build()
                    );
                }else {
                    return Arrays.asList(
                            EmailTemplate.builder()
                                    .body(EMAIL_BODY_WITHOUT_INVOICE)
                                    .header(EMAIL_HEADER)
                                    .build(),
                            SmsTemplate.builder()
                                    .body(SMS_BODY_WITHOUT_INVOICE)
                                    .build()
                    );
                }
            default:
                log.error("Invalid event type");
                return Collections.EMPTY_LIST;
        }
    }

}
