package com.para4digm.yumcdpl.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private Integer id;
    private String name;
    private double money;
    private Date insertTime;
}
