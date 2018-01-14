package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by deepak.dhakad on 1/13/18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse <T> {
    private int status;
    private T data;
    private String message;
}
