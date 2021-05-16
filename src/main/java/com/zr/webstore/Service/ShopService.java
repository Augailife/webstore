package com.zr.webstore.Service;

import com.alibaba.fastjson.JSON;
import com.zr.webstore.DTO.OrderCacheDTO;
import com.zr.webstore.DTO.OrderCreateDTO;
import com.zr.webstore.DTO.ResultDTO;
import com.zr.webstore.enums.ExceptionCode;
import com.zr.webstore.enums.RedisEnum;
import com.zr.webstore.mapper.OrderItemMapper;
import com.zr.webstore.mapper.ProductMapper;
import com.zr.webstore.model.OrderItem;
import com.zr.webstore.model.Product;
import com.zr.webstore.model.ProductExample;
import com.zr.webstore.utils.RedisUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShopService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    AsyncService asyncService;

    @Transactional
    public Object shop(OrderCreateDTO orderCreateDTO) {
        if(redisUtils.lGetListSize(RedisEnum.QUEUE_WAIT_FOR.getName() + orderCreateDTO.getId())<orderCreateDTO.getCount()){
            return ResultDTO.errorOf(ExceptionCode.SHOP_MORE);
        }
        if(redisUtils.lGetListSize(RedisEnum.QUEUE_WAIT_FOR.getName() + orderCreateDTO.getId())==0){
            return ResultDTO.errorOf(ExceptionCode.NO_STOCK);
        }
        else {
//            商品库存队列弹出count个
            for(int i=0;i<orderCreateDTO.getCount();i++){
                redisUtils.lGet(RedisEnum.QUEUE_WAIT_FOR.getName() + orderCreateDTO.getId());
            }
//          从redis中取出product
            Product product=(Product) redisUtils.hget(RedisEnum.queryEnum(orderCreateDTO.getType()).getName(), orderCreateDTO.getId().toString());
//            执行减库存,加销量操作
            product.setStock(orderCreateDTO.getStock()-orderCreateDTO.getCount());
            product.setSales(product.getSales()+orderCreateDTO.getCount());
//            删除原有redis书籍信息，设置新的进去
            redisUtils.hdel(RedisEnum.queryEnum(orderCreateDTO.getType()).getName(), orderCreateDTO.getId().toString());
            redisUtils.hset(RedisEnum.queryEnum(orderCreateDTO.getType()).getName(), orderCreateDTO.getId().toString(), product);
//            更改数据库中的信息
            ProductExample productExample=new ProductExample();
            productExample.createCriteria()
                    .andIdEqualTo(orderCreateDTO.getId());
            productMapper.updateByExampleSelective(product,productExample);
//            生成订单
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(UUID.randomUUID().toString());
            orderItem.setOrderProduct(orderCreateDTO.getName());
            orderItem.setOrderCount(orderCreateDTO.getCount());
            orderItem.setProductPrice(orderCreateDTO.getPrice());
            orderItem.setPrice(orderCreateDTO.getPrice().multiply(new BigDecimal(orderCreateDTO.getCount())));
            orderItem.setCreateTime(System.currentTimeMillis());
            orderItem.setStatus(0);
            orderItem.setUserid(orderCreateDTO.getUserId());
//            将订单插入数据库
            orderItemMapper.insert(orderItem);
            return orderItem;
        }
    }

    public String seckill(OrderCreateDTO orderCreateDTO) {
//        boolean b = redisUtils.sgetKey(RedisEnum.USER_REPEAT_SECKILL.getName(), orderCreateDTO.getUserId());
//        if(b){
//         return "您已提交购买信息，请耐心等待";
//        }
        if(!redisUtils.lGet(RedisEnum.QUEUE_WAIT_FOR.getName() + orderCreateDTO.getId())){
            return "您的手慢了一步，商品已售罄";
        }else {
//            将用户加入set队列
//            redisUtils.sSet(RedisEnum.USER_REPEAT_SECKILL.getName(), orderCreateDTO.getUserId());
//            加入线程队列，准备提交给异步线程处理
            OrderCacheDTO orderCacheDTO = new OrderCacheDTO(orderCreateDTO.getUserId(),orderCreateDTO.getPrice(),orderCreateDTO.getId(),orderCreateDTO.getName(),orderCreateDTO.getType());
            redisUtils.lSet(RedisEnum.THREAD_WAIT_QUEUE.getName(),orderCacheDTO);
//            异步处理
            asyncService.shopAsync();
            return "您已秒杀成功,可在我的订单处查看订单信息";
        }
    }

    public List<Product> querySeckillBook() {
        List<Object> objects = redisUtils.lGet(RedisEnum.SECKILL_HTML_PRODUCT.getName(), 0, -1);
        List<Product> productList = objects.stream().map(object ->  JSON.parseObject(JSON.toJSONString(object),Product.class)).collect(Collectors.toList());
        List<Product> productList1=new ArrayList<>();
        for(Product product:productList){
            Object hget = redisUtils.hget(RedisEnum.queryEnum(product.getType()).getName(), product.getId().toString());
            productList1.add((Product) hget);
        }
        return productList1;
    }
}
