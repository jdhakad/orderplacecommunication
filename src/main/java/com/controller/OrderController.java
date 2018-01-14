package com.controller;

import com.pojo.BaseResponse;
import com.pojo.BaseRequest;
import com.pojo.OrderPlacedBO;
import com.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by deepak.dhakad on 1/12/18.
 */
@RestController
@RequestMapping(value = "/order")
@Slf4j(topic = "req-res-logger")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/place", method = RequestMethod.POST)
    public BaseResponse placeOrder(@RequestBody OrderPlacedBO orderBO) {
        return new BaseResponse(0, orderService.placeOrder(orderBO), "success");

    }

    @RequestMapping(value = "/picked_up", method = RequestMethod.POST)
    public BaseResponse placeOrder(@RequestBody BaseRequest baseRequest) {
        if (baseRequest.getInvoiceDetails() == null) {
            return new BaseResponse(1, null, "invoice not present");
        }
        return new BaseResponse(0, orderService.pickUpOrder(baseRequest), "success");

    }
}

