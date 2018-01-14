package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by deepak.dhakad on 1/12/18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    private long id;
    private String name;
    private int quantity;
    private double price;
}
