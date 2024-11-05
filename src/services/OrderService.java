package services;
import Enums.Status;
import models.Order;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class OrderService {
    private  Map<Integer,Order>  orders;
    public static volatile OrderService instance;
    private static ProductService productServiceInstance;
    private OrderService() {
        orders = new HashMap<>();
    }
    public static OrderService getInstance() {
       if (instance == null) {
           synchronized (OrderService.class) {
               if (instance == null) {
                   instance = new OrderService();
                   productServiceInstance=ProductService.getInstance();
               }
           }
       }
        return instance;
    }
    public Map<Integer,Order> getOrders() {
        return orders;
    }

    public void generateOrder(Integer orderID, Map<String,Integer> requiredProducts)
    {
        if(orders.containsKey(orderID))
        {
           System.out.println("Order already exists");
        }
        Order o= new Order(orderID,requiredProducts);
        orders.put(orderID, o);
        System.out.println("Order created: "+orderID);
    }
    public boolean confirmOrder(Integer orderID)
    {
        if(!orders.containsKey(orderID))
        {
             System.out.println("Order does not exist");
             return false;
        }
        if(orders.get(orderID).getStatus()== Status.CONFIRMED)
        {
            System.out.println("Order already confirmed");
            return false;
        }
        //remove from inventory
          Map<String,Integer> toRemoveProducts=orders.get(orderID).getProducts();
          if(productServiceInstance.removeProducts(toRemoveProducts))
         {
             //set the status as CONFIRMED.s
             orders.get(orderID).setStatus(Status.CONFIRMED);
             return true;
         }
          return false;
    }
    public void printALLOrderStatus(){
        System.out.println("Printing all order status");
        orders.forEach((orderID,Order)->{
            System.out.println(orderID+""+Order.getStatus());
            Order.getProducts().forEach((pName, quantity)->{
                 System.out.print(pName+": "+quantity+" ");
            });
        });
    }
}
