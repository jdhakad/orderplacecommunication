package com;

import com.pojo.BaseRequest;
import com.pojo.OrderPlacedBO;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by deepak.dhakad on 1/14/18.
 */
public class TemplateUtil {

    public static Map<String, Object> getTemplateCtx(BaseRequest orderBO) {
        Map<String, Object> ctx = new HashMap<>();
        ctx.put("EVENT_TYPE", "ORDER_PLACED");
        if (orderBO.getInvoiceDetails() != null) {
            ctx.put("INVOICE_PRESENT", "true");
        } else {
            ctx.put("INVOICE_PRESENT", "false");
        }
        return ctx;
    }

    public static Map<String, Object> getParams(BaseRequest orderBO) {
        Map<String, Object> params = new HashMap<>();
        Optional.ofNullable(orderBO.getInvoiceDetails())
                .ifPresent(invoiceDetails -> params.put("invoiceDetails", invoiceDetails));
        Optional.ofNullable(orderBO.getUserDetails())
                .ifPresent(userDetails -> {
                    Optional.ofNullable(userDetails.getId())
                            .ifPresent(userId -> params.put("userId", String.valueOf(userId)));
                    Optional.ofNullable(userDetails.getCity())
                            .ifPresent(city -> params.put("city", city));
                    Optional.ofNullable(userDetails.getArea())
                            .ifPresent(area -> params.put("area", area));
                    Optional.ofNullable(userDetails.getAddress())
                            .ifPresent(address -> params.put("address", address));
                    Optional.ofNullable(userDetails.getMobileNumber())
                            .ifPresent(mobile -> params.put("mobile", userDetails.getMobileNumber()));
                    Optional.ofNullable(userDetails.getName())
                            .ifPresent(userName -> params.put("userName", userName));
                });
        Optional.ofNullable(orderBO.getId())
                .ifPresent(orderId -> params.put("orderId", String.valueOf(orderId)));
        params.put("companyName", "Tangy Street");

        return params;
    }
}
