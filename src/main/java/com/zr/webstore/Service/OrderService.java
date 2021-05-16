package com.zr.webstore.Service;

import com.alibaba.fastjson.JSON;
import com.zr.webstore.DTO.OrderCacheDTO;
import com.zr.webstore.enums.RedisEnum;
import com.zr.webstore.mapper.OrderItemMapper;
import com.zr.webstore.model.OrderItem;
import com.zr.webstore.model.OrderItemExample;
import com.zr.webstore.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    RedisUtils redisUtils;
    public List<OrderItem> getUserOrder(Integer userId) {
        //        查出全部order
        OrderItemExample orderItemExample=new OrderItemExample();
        //        时间倒序排列
        orderItemExample.setOrderByClause("create_time desc");
        orderItemExample.createCriteria()
                    .andUseridEqualTo(userId);
        List<OrderItem> orderItems = orderItemMapper.selectByExample(orderItemExample);
        return orderItems;
//        Object o=redisUtils.lGet(RedisEnum.USER_ORDERITEM.getName()+userId);
//        OrderItem orderItem=JSON.parseObject(JSON.toJSONString(o),OrderItem.class);
//        if(orderItem==null){
//        }
    }
}
