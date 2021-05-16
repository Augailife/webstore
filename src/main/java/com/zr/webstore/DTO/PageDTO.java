package com.zr.webstore.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDTO<T> {
//   当前页码
    private Integer page;
    private Integer type;
//    页面中的数据
    private List<T> data;
//    是否有第一页
    private boolean hasfirst;
//    是否有最后一页
    private boolean haslast;
//    是否有下一页
    private boolean hasnext;
    //    是否有上一页
    private boolean hasprivious;
//    页数集合
    private List<Integer> pages=new ArrayList<>();
//    总页数
    private Integer totalPage;

    /**
     * 分页逻辑
     * @param pageNow 当前页码
     * @param size 每页容量
     * @param count 总数量
     */
    public void paging(Integer pageNow, Integer size, Integer count) {
        page=pageNow;
            if(count%size==0){
            totalPage=count/size;
        }else{
            totalPage=count/size+1;
        }
        if(count<=size){
            totalPage=1;
        }
        if(page<1){
            page=1;
        }
        if(page>=totalPage){
            page=totalPage;
        }
        pages.add(page);
        for(int i=1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);//往前加入0的位置（头部插入）。
            }
            if(page+i<=totalPage){
                pages.add(page+i);//往后就顺延着尾部插入即可。
            }
        }
        if(page==1){
            hasprivious=false;
        }else{
            hasprivious=true;
        }
        if(page==totalPage){
            hasnext=false;
        }else{
            hasnext=true;
        }
        if(pages.contains(1)){
            hasfirst=false;
        }else{
            hasfirst=true;
        }
        if(pages.contains(totalPage)){
            haslast=false;
        }else{
            haslast=true;
        }
    }

}
