package com.zr.webstore.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderItem implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.order_id
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    private String orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.order_product
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    private String orderProduct;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.order_count
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    private Integer orderCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.product_price
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    private BigDecimal productPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.price
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    private BigDecimal price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.create_time
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    private Long createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.status
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_item.userId
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    private Integer userid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.order_id
     *
     * @return the value of order_item.order_id
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.order_id
     *
     * @param orderId the value for order_item.order_id
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.order_product
     *
     * @return the value of order_item.order_product
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public String getOrderProduct() {
        return orderProduct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.order_product
     *
     * @param orderProduct the value for order_item.order_product
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public void setOrderProduct(String orderProduct) {
        this.orderProduct = orderProduct == null ? null : orderProduct.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.order_count
     *
     * @return the value of order_item.order_count
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public Integer getOrderCount() {
        return orderCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.order_count
     *
     * @param orderCount the value for order_item.order_count
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.product_price
     *
     * @return the value of order_item.product_price
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.product_price
     *
     * @param productPrice the value for order_item.product_price
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.price
     *
     * @return the value of order_item.price
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.price
     *
     * @param price the value for order_item.price
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.create_time
     *
     * @return the value of order_item.create_time
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.create_time
     *
     * @param createTime the value for order_item.create_time
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.status
     *
     * @return the value of order_item.status
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.status
     *
     * @param status the value for order_item.status
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_item.userId
     *
     * @return the value of order_item.userId
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_item.userId
     *
     * @param userid the value for order_item.userId
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}