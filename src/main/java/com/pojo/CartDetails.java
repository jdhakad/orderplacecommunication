package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Created by deepak.dhakad on 1/14/18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDetails {
    private List<Item> itemList;
    private double deliveryCharge;
    private double tax;
    private double orderTotal;
}
