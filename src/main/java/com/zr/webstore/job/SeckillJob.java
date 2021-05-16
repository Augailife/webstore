package com.zr.webstore.job;

import com.zr.webstore.enums.RedisEnum;
import com.zr.webstore.mapper.OrderItemMapper;
import com.zr.webstore.mapper.ProductMapper;
import com.zr.webstore.mapper.UserMapper;
import com.zr.webstore.model.*;
import com.zr.webstore.utils.RedisUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SeckillJob extends QuartzJobBean {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ProductExample productExample = new ProductExample();
        productExample.createCriteria()
                .andStockGreaterThan(0);
        List<Product> products = productMapper.selectByExample(productExample);
        for(Product product:products){
            createQueue(product.getStock(), product.getId());
        }
        List<Product> olds = products.stream().filter(product -> product.getType() == 1).collect(Collectors.toList());
        List<Product> sciences = products.stream().filter(product -> product.getType() == 2).collect(Collectors.toList());
        List<Product> foreigns = products.stream().filter(product -> product.getType() == 3).collect(Collectors.toList());
        List<Product> naturals = products.stream().filter(product -> product.getType() == 4).collect(Collectors.toList());
        List<Product> educations = products.stream().filter(product -> product.getType() == 5).collect(Collectors.toList());

        Map<String, Object> oldMap = olds.stream().collect(Collectors.toMap(old -> old.getId().toString(), old -> old));
        Map<String, Object> scienceMap = sciences.stream().collect(Collectors.toMap(science -> science.getId().toString(), science -> science));
        Map<String, Object> foreignMap = foreigns.stream().collect(Collectors.toMap(foreign -> foreign.getId().toString(), foreign -> foreign));
        Map<String, Object> naturalMap = naturals.stream().collect(Collectors.toMap(natural -> natural.getId().toString(), natural -> natural));
        Map<String, Object> educationMap = educations.stream().collect(Collectors.toMap(education -> education.getId().toString(), education -> education));

        redisUtils.hmset(RedisEnum.OLDYEARBOOK.getName(), oldMap);
        redisUtils.hmset(RedisEnum.SCIENCE.getName(), scienceMap);
        redisUtils.hmset(RedisEnum.FOREIGN_LTT.getName(), foreignMap);
        redisUtils.hmset(RedisEnum.NATURAL.getName(), naturalMap);
        redisUtils.hmset(RedisEnum.EDUCATION.getName(), educationMap);
    }
    public void createQueue(Integer count,Integer id){
        long size = redisUtils.lGetListSize(RedisEnum.QUEUE_WAIT_FOR.getName() + id);
        if(size==count){
            return;
        }
        if(size!=0){
            return;
        }else {
            for(int i=0;i<count;i++){
                redisUtils.lSet(RedisEnum.QUEUE_WAIT_FOR.getName() + id,id);
            }
        }
    }
}
