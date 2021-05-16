package com.zr.webstore.mapper;

import com.zr.webstore.DTO.ProductQueryDTO;
import com.zr.webstore.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ProductExtMapper {
    List<Product> incSelectAll();
    List<Product> searchProduct(ProductQueryDTO productQueryDTO);
    Integer searchCount(ProductQueryDTO productQueryDTO);
}
