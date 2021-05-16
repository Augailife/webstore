package com.zr.webstore.Service;

import com.alibaba.fastjson.JSON;
import com.zr.webstore.DTO.OrderCacheDTO;
import com.zr.webstore.enums.RedisEnum;
import com.zr.webstore.mapper.OrderItemMapper;
import com.zr.webstore.mapper.ProductMapper;
import com.zr.webstore.model.OrderItem;
import com.zr.webstore.model.Product;
import com.zr.webstore.model.ProductExample;
import com.zr.webstore.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Service
public class AsyncService {
    private static final Logger logger = LoggerFactory.getLogger(AsyncService.class);
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductMapper productMapper;
    public static Integer count=0;
    @Async("asyncServiceExecutor")
    public void shopAsync() {
//        logger.info("异步线程开始处理");
//         从订单队列中获取一个进行处理
        OrderCacheDTO orderCacheDTO;
        synchronized (count){
            //            将用户从线程等待队列移除并获取最后一个
            Object o = redisUtils.lGetO(RedisEnum.THREAD_WAIT_QUEUE.getName());
            orderCacheDTO=JSON.parseObject(JSON.toJSONString(o),OrderCacheDTO.class);
            if(orderCacheDTO==null){
                return;
            }
            orderCacheDTO.setWaitId(count);
            if(count<=1000){
                count=count+1;
            }
            ProductExample productExample=new ProductExample();
            productExample.createCriteria()
                    .andIdEqualTo(orderCacheDTO.getProductId());
//           从redis中取出订单书籍并进行更新
            Product product=(Product) redisUtils.hget(RedisEnum.queryEnum(orderCacheDTO.getType()).getName(), orderCacheDTO.getProductId().toString());
//            product.setStock(product.getStock()-1);
//            product.setSales(product.getSales()+1);
//            redisUtils.hdel(RedisEnum.queryEnum(orderCacheDTO.getType()).getName(),orderCacheDTO.getProductId().toString());
//            redisUtils.hset(RedisEnum.queryEnum(orderCacheDTO.getType()).getName(),orderCacheDTO.getProductId().toString(),product);
            if(product==null){
                product = productMapper.selectByPrimaryKey(orderCacheDTO.getProductId());
                product.setStock(product.getStock()-1);
                product.setSales(product.getSales()+1);
                redisUtils.hdel(RedisEnum.queryEnum(orderCacheDTO.getType()).getName(),orderCacheDTO.getProductId().toString());
                redisUtils.hset(RedisEnum.queryEnum(orderCacheDTO.getType()).getName(),orderCacheDTO.getProductId().toString(),product);
            }else {
                product.setStock(product.getStock()-1);
                product.setSales(product.getSales()+1);
                redisUtils.hdel(RedisEnum.queryEnum(orderCacheDTO.getType()).getName(),orderCacheDTO.getProductId().toString());
                redisUtils.hset(RedisEnum.queryEnum(orderCacheDTO.getType()).getName(),orderCacheDTO.getProductId().toString(),product);
            }
//            更新数据库
            productMapper.updateByExampleSelective(product,productExample);
//            生成订单
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(UUID.randomUUID().toString());
            orderItem.setOrderProduct(orderCacheDTO.getProductName());
            orderItem.setOrderCount(1);
//            计算订单价格
            orderCacheDTO=discount(orderCacheDTO);
            orderItem.setProductPrice(orderCacheDTO.getPrice());
            orderItem.setPrice(orderCacheDTO.getPrice());
            orderItem.setCreateTime(System.currentTimeMillis());
            orderItem.setStatus(0);
            orderItem.setUserid(orderCacheDTO.getUserId());
//            更新数据库
            orderItemMapper.insert(orderItem);
//            redisUtils.setRemove(RedisEnum.USER_REPEAT_SECKILL.getName(),orderCacheDTO.getUserId());
//            redis加入抢购成功名单
            if(orderCacheDTO.getWaitId()<1000){
                redisUtils.lSet(RedisEnum.SECKILL_SUCCESS_ORDER.getName(),orderCacheDTO);
            }
        }
    }

    public OrderCacheDTO discount(OrderCacheDTO orderCacheDTO){
        if(orderCacheDTO.getWaitId()<=100){
            orderCacheDTO.setPrice(orderCacheDTO.getPrice().divide(new BigDecimal(10)).setScale(2,BigDecimal.ROUND_DOWN));
        }else if(orderCacheDTO.getWaitId()<=500){
            orderCacheDTO.setPrice(orderCacheDTO.getPrice().divide(new BigDecimal(2)).setScale(2,BigDecimal.ROUND_DOWN));
        }else if (orderCacheDTO.getWaitId()<=1000){
            orderCacheDTO.setPrice(orderCacheDTO.getPrice().multiply(new BigDecimal(0.8)).setScale(2,BigDecimal.ROUND_DOWN));
        }
        return orderCacheDTO;
    }
}
