package com.pojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Date;

/**
 * Created by deepak.dhakad on 1/12/18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceDetails {
    private String invoiceNumber;
    private List<Item> itemList;
    private double deliveryCharge;
    private double tax;
    private double orderTotal;
    private Date invoiceDate;
}
