package models;

import Enums.Status;

;import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

public class Order {
    private int orderID;
    private Status status;
    private String orderCreatedAt;
    private Map<String,Integer> products;
    public Order(int orderID, Map<String,Integer> reqProducts) {
        status=Status.PENDING;
        orderCreatedAt= LocalDateTime.now().toString();
        this.products=reqProducts;
    }
    public int getOrderID() {
        return orderID;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public Status getStatus() {
        return status;
    }

    public String getOrderCreatedAt() {
        return orderCreatedAt;
    }
    public Map<String,Integer> getProducts() {
     return products;
    }
}