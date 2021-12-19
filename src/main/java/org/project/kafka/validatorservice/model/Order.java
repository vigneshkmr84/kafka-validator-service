package org.project.kafka.validatorservice.model;

import java.util.Date;

public class Order {

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String product;
    private String address;
    private String phone_number;
    private int count;
    private double cost;
    private String currency;

    private Date processedTimeStamp;

    private String orderId;

    public Order() {
    }

    public Order(int id, String first_name, String last_name, String email, String product, String address, String phone_number, int count, double cost, String currency, Date processedTimeStamp, String orderId) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.product = product;
        this.address = address;
        this.phone_number = phone_number;
        this.count = count;
        this.cost = cost;
        this.currency = currency;
        this.processedTimeStamp = processedTimeStamp;
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getProcessedTimeStamp() {
        return processedTimeStamp;
    }

    public void setProcessedTimeStamp(Date processedTimeStamp) {
        this.processedTimeStamp = processedTimeStamp;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", product='" + product + '\'' +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", count=" + count +
                ", cost=" + cost +
                ", currency='" + currency + '\'' +
                ", processedTimeStamp=" + processedTimeStamp +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}

