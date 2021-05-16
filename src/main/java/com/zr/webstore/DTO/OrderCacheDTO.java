package com.zr.webstore.DTO;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderCacheDTO {
    private Integer userId;
    private BigDecimal price;
    private Integer productId;
    private String productName;
//    第几个买到的
    private Integer waitId;
    private Integer type;

    public OrderCacheDTO() {
    }

    public OrderCacheDTO(Integer userId, BigDecimal price, Integer productId, String productName, Integer type) {
        this.userId = userId;
        this.price = price;
        this.productId = productId;
        this.productName = productName;
        this.type = type;
    }
}
