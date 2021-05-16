package com.zr.webstore.enums;

public enum  RedisEnum {
    OLDYEARBOOK(1,"OLDYEARBOOK"),
    SCIENCE(2,"SCIENCE"),
    FOREIGN_LTT(3,"FOREIGN_LTT"),
    NATURAL(4,"NATURAL"),
    EDUCATION(5,"EDUCATION"),
    //    秒杀成功订单集合,
    SECKILL_SUCCESS_ORDER(6,"SECKILL_SUCCESS_ORDER"),
    //    每一个用户的订单队列
    USER_ORDERITEM(7,"USER_ORDERITEM"),
//    用户重复购买队列
    USER_REPEAT_SECKILL(8,"USER_REPEAT_SECKILL"),
//    书库存
    QUEUE_WAIT_FOR(9,"QUEUE_WAIT_FOR"),
//    异步线程处理等待队列
    THREAD_WAIT_QUEUE(10,"THREAD_WAIT_QUEUE"),
//    秒杀页面商品队列
    SECKILL_HTML_PRODUCT(11,"SECKILL_HTML_PRODUCT")
    ;
    String name;
    Integer type;

    RedisEnum(Integer type,String name) {
        this.name = name;
        this.type=type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static RedisEnum queryEnum(Integer type){
        switch (type){
            case 1:
                return RedisEnum.OLDYEARBOOK;
            case 2:
                return RedisEnum.SCIENCE;
            case 3:
                return RedisEnum.FOREIGN_LTT;
            case 4:
                return RedisEnum.NATURAL;
            case 5:
                return RedisEnum.EDUCATION;
        }
        return null;
    }
}
