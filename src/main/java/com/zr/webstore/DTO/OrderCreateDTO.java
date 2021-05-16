package com.zr.webstore.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderCreateDTO {
//    产品id
    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private Integer count;

    private Integer userId;

    private Integer type;

}
