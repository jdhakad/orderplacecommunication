package com.pojo;

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
@Builder
public class UserDetails {
    private long id;
    private String name;
    private String emailId;
    private String mobileNumber;
    private String address;
    private String city;
    private String area;
}
