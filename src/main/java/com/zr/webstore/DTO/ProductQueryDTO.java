package com.zr.webstore.DTO;

import lombok.Data;

@Data
public class ProductQueryDTO {
    private Integer startPage;
    private Integer size;
    private String search;
}
