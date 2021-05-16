package com.zr.webstore.job;

import com.zr.webstore.Service.AsyncService;
import com.zr.webstore.enums.RedisEnum;
import com.zr.webstore.mapper.ProductExtMapper;
import com.zr.webstore.mapper.ProductMapper;
import com.zr.webstore.model.Product;
import com.zr.webstore.utils.RedisUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SeckillHtmlJob extends QuartzJobBean {
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    ProductExtMapper productExtMapper;
    @Autowired
    ProductMapper productMapper;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //        下一轮秒杀之前，清空count以及秒杀成功订单
        AsyncService.count=0;
        for(int i=0;i<redisUtils.lGetListSize(RedisEnum.SECKILL_SUCCESS_ORDER.getName());i++){
            redisUtils.lGet(RedisEnum.SECKILL_SUCCESS_ORDER.getName());
        }
        List<Product> productList = productExtMapper.incSelectAll();
        List<Integer> productId=new ArrayList<>();
        Integer idArray[]=new Integer[productList.size()];
        for(Product product:productList){
            productId.add(product.getId());
        }
        productId.toArray(idArray);
        Random random=new Random();
        for (int i=0;i<3;i++){
            int id = random.nextInt(productList.size() - 1);
            Product product = productMapper.selectByPrimaryKey(idArray[id]);
            redisUtils.lSet(RedisEnum.SECKILL_HTML_PRODUCT.getName(), product);

            if(redisUtils.lGetListSize(RedisEnum.SECKILL_HTML_PRODUCT.getName())>3){
                redisUtils.lGet(RedisEnum.SECKILL_HTML_PRODUCT.getName());
            }
        }
    }
}
