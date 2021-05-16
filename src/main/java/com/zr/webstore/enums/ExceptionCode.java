package com.zr.webstore.enums;

public enum  ExceptionCode {
    LONGIN_NOT(2001,"当前操作需要先进行登录哦~"),
    SYS_ERROR(2002,"系统故障"),
    USERTYPE_ERROR(2003,"大兄弟，你怎么想看后台信息呢"),
    SHOP_MORE(2004,"购买的太多了哦"),
    NO_STOCK(2005,"您的手慢了一步，商品已售罄")
    ;
    private Integer code;
    private String message;

    ExceptionCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
