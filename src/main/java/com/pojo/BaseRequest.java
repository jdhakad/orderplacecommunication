package com.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by deepak.dhakad on 1/14/18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseRequest {
    protected long id;
    protected InvoiceDetails invoiceDetails;
    protected UserDetails userDetails;
}
