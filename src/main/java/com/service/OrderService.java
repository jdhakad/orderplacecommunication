package com.service;

import com.amqp.AmqpSender;
import com.Utility;
import com.pojo.BaseRequest;
import com.pojo.OrderPlacedBO;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by deepak.dhakad on 1/12/18.
 */
@Service
public class OrderService {

    @Autowired
    @Qualifier("eventExchange")
    private TopicExchange eventExchange;


    @Autowired
    private AmqpSender amqpSender;

    @Autowired
    private InvoiceService invoiceService;

    public OrderPlacedBO placeOrder(OrderPlacedBO orderBO) {
        invoiceService.createInvoice(orderBO);
        amqpSender.send(eventExchange, "order.placed", Utility.serialize(orderBO));
        return orderBO;
    }

    public Object pickUpOrder(BaseRequest baseRequest) {
        amqpSender.send(eventExchange, "order.pickedup", Utility.serialize(baseRequest));
        return baseRequest;
    }
}
