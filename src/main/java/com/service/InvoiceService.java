package com.service;

import com.pojo.InvoiceDetails;
import com.pojo.Item;
import com.pojo.OrderPlacedBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by deepak.dhakad on 1/14/18.
 */
@Service
@Slf4j
public class InvoiceService {

    public InvoiceDetails createInvoice(OrderPlacedBO orderBO) {
        InvoiceDetails invoiceDetails = null;
        if (orderBO.isInvoiceGenerated()) {
            invoiceDetails =
                    InvoiceDetails.builder()
                            .invoiceNumber(UUID.randomUUID().toString())
                            .deliveryCharge(orderBO.getCartDetails().getDeliveryCharge())
                            .itemList(orderBO.getCartDetails().getItemList())
                            .invoiceDate(new Date())
                            .tax(orderBO.getCartDetails().getTax())
                            .build();
            double orderTotal = invoiceDetails.getItemList().stream().mapToDouble(Item::getPrice).sum() + invoiceDetails.getDeliveryCharge()
                    + invoiceDetails.getTax();
            invoiceDetails.setOrderTotal(orderTotal);
        }
        orderBO.setInvoiceDetails(invoiceDetails);
        return invoiceDetails;
    }
}

