package com.zr.webstore.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.id
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.author
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    private String author;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.name
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.price
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    private BigDecimal price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.sales
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    private Integer sales;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.type
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.message
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    private String message;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.stock
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    private Integer stock;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.img_path
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    private String imgPath;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.id
     *
     * @return the value of product.id
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.id
     *
     * @param id the value for product.id
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.author
     *
     * @return the value of product.author
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public String getAuthor() {
        return author;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.author
     *
     * @param author the value for product.author
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.name
     *
     * @return the value of product.name
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.name
     *
     * @param name the value for product.name
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.price
     *
     * @return the value of product.price
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.price
     *
     * @param price the value for product.price
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.sales
     *
     * @return the value of product.sales
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public Integer getSales() {
        return sales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.sales
     *
     * @param sales the value for product.sales
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public void setSales(Integer sales) {
        this.sales = sales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.type
     *
     * @return the value of product.type
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.type
     *
     * @param type the value for product.type
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.message
     *
     * @return the value of product.message
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.message
     *
     * @param message the value for product.message
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.stock
     *
     * @return the value of product.stock
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.stock
     *
     * @param stock the value for product.stock
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.img_path
     *
     * @return the value of product.img_path
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.img_path
     *
     * @param imgPath the value for product.img_path
     *
     * @mbg.generated Sun Apr 18 12:27:21 CST 2021
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }
}