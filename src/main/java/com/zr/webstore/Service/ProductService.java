package com.zr.webstore.Service;

import com.zr.webstore.DTO.PageDTO;
import com.zr.webstore.DTO.ProductQueryDTO;
import com.zr.webstore.enums.RedisEnum;
import com.zr.webstore.mapper.ProductExtMapper;
import com.zr.webstore.model.Product;
import com.zr.webstore.model.ProductExample;
import com.zr.webstore.utils.RedisUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    ProductExtMapper productExtMapper;
//    搜索分页
    public PageDTO list(String search, Integer pageNow, Integer size) {
        if (search != null || search != "") {
            String[] s = search.split(" ");
            String name = Arrays.stream(s).collect(Collectors.joining("|"));
//            利用字符串和startPage查出书籍的信息
            PageDTO<Product> pageDTO=new PageDTO<>();
            ProductQueryDTO productQueryDTO = new ProductQueryDTO();
            productQueryDTO.setSearch(name);
            Integer count = productExtMapper.searchCount(productQueryDTO);
            productQueryDTO.setSize(size);
            pageDTO.paging(pageNow, size, count);
            Integer startPage=(pageDTO.getPage()-1)*size;
            productQueryDTO.setStartPage(startPage);
            List<Product> productList = productExtMapper.searchProduct(productQueryDTO);
            pageDTO.setData(productList);
            return pageDTO;
        }else{
            return null;
        }
    }
    public PageDTO<Product> list(Integer size, Integer pageNow,Integer type) {
        PageDTO<Product> pageDTO=new PageDTO<>();
//        从redis中获取书籍信息
        Map<Object, Object> productMap = redisUtils.hmget(RedisEnum.queryEnum(type).getName());
        List<Product> productAllList=new ArrayList<>();
        for(Object obj:productMap.values()){
            productAllList.add((Product) obj);
        }
        pageDTO.paging(pageNow, size, productAllList.size());
        pageDTO.setType(type);
//        查询出本页第一个产品在集合中排第几
        Integer startId=(pageDTO.getPage()-1)*size;
        List<Product> productList=new ArrayList<>();
        if(productAllList.size()<size){
            size=productAllList.size();
        }
        if(pageNow==pageDTO.getTotalPage()){
            for(int i=startId;i<productAllList.size();i++){
                productList.add(productAllList.get(i));
            }
        }else {
            for(int i=startId;i<startId+size;i++){
                productList.add(productAllList.get(i));
            }
        }
        pageDTO.setData(productList);
        return pageDTO;
    }

    public Product getProductById(Integer productId,Integer type) {
        Product product = (Product)redisUtils.hget(RedisEnum.queryEnum(type).getName(), productId.toString());
        return product;

    }
}
