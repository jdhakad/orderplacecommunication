package com.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by deepak.dhakad on 1/12/18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlacedBO extends BaseRequest{
    private CartDetails cartDetails;
    private boolean invoiceGenerated;
}
