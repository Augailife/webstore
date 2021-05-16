package com.zr.webstore.Service;

import com.zr.webstore.DTO.PageDTO;
import com.zr.webstore.enums.RedisEnum;
import com.zr.webstore.mapper.ProductExtMapper;
import com.zr.webstore.mapper.ProductMapper;
import com.zr.webstore.model.Product;
import com.zr.webstore.model.ProductExample;
import com.zr.webstore.utils.RedisUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BookManageService {
    @Autowired
    ProductExtMapper productExtMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    RedisUtils redisUtils;
    @Value("${web.upload-path}")
    String uploadPath;
    private static final Logger LOGGER = LoggerFactory.getLogger(BookManageService.class);
    public PageDTO<Product> list(Integer size, Integer pageNow) {
        PageDTO<Product> pageDTO=new PageDTO<>();
//        计算从第几条开始查
        Integer startpage=(pageNow-1)*size;
        ProductExample productExample = new ProductExample();
        productExample.createCriteria()
                .andTypeGreaterThan(0);
//        查出数据
        List<Product> productList = productMapper.selectByExampleWithRowbounds(productExample, new RowBounds(startpage, size));
        long count = productMapper.countByExample(productExample);
//        完善分页模型
        pageDTO.paging(pageNow, size, (int)count);
        pageDTO.setData(productList);
        return pageDTO;
    }

    public String insert(Product product) {
//        insert返回新增数据的id,返回到product对象中
        Integer success = productMapper.insert(product);
        boolean s =redisUtils.hset(RedisEnum.queryEnum(product.getType()).getName(),product.getId().toString(),product);
        createQueue(product.getStock(),product.getId());
        if(success!=0&&s){
            return "插入成功";
        }else {
            return "插入失败";
        }
    }

    public String delete(Product product) {
        int i = productMapper.deleteByPrimaryKey(product.getId());
        boolean d=redisUtils.hdel(RedisEnum.queryEnum(product.getType()).getName(),product.getId().toString());
        if(i!=0&&d){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    public String update(Product product) {
        Integer type = productMapper.selectByPrimaryKey(product.getId()).getType();
        int i = productMapper.updateByPrimaryKeySelective(product);
        Product product1 = productMapper.selectByPrimaryKey(product.getId());
//        使用修改前的type删除
        boolean d=redisUtils.hdel(RedisEnum.queryEnum(type).getName(),product.getId().toString());
//        修改后的type插入
        boolean s =redisUtils.hset(RedisEnum.queryEnum(product.getType()).getName(),product.getId().toString(),product1);
//        更新库存队列
        createQueue(product.getStock(),product.getId());
        if(i!=0&&d&&s){
            return "更新成功";
        }else {
            return "更新失败";
        }
    }
    public String upload(MultipartFile file){
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileNamey = file.getOriginalFilename();
        String[] split = fileNamey.split("\\.");

        String fileName = UUID.randomUUID() +"."+split[split.length-1];
        String filePath = uploadPath;
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
            return fileName;
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return "上传失败";
    }

        public Product selectById(Integer productId,Integer type) {
            Object product = redisUtils.hget(RedisEnum.queryEnum(type).getName(), productId.toString());
            return (Product)product;
    }
    public void createQueue(Integer count,Integer id){
        long size = redisUtils.lGetListSize(RedisEnum.QUEUE_WAIT_FOR.getName() + id);
        if(size==count){
            return;
        }else if(size<count){
            for(int i=0;i<count-size;i++){
                redisUtils.lSet(RedisEnum.QUEUE_WAIT_FOR.getName() + id,id);
            }
        }else if(size>count){
            for(int i=0;i<size-count;i++){
                redisUtils.lGet(RedisEnum.QUEUE_WAIT_FOR.getName()+id);
            }
        }
    }
}
