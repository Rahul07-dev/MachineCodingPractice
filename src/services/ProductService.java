package services;

import models.Product;

import javax.lang.model.type.NullType;
import javax.naming.directory.InvalidAttributesException;
import javax.security.auth.login.AccountNotFoundException;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductService {
    private final Map<String,Integer> Inventory;
    private static volatile  ProductService productServiceInstance;
    private ProductService() {
        Inventory = new HashMap<>();
    }
    public static ProductService getInstance() {
        if(productServiceInstance==null)
        {
            synchronized (ProductService.class) {
                if(productServiceInstance==null) {
                    productServiceInstance = new ProductService();
                }
            }
        }
        return productServiceInstance;
    }
    public void addProductToInventory(Product p, Integer quantity){
        Inventory.put(p.getProductName(),quantity);
    }
    private void removeProductFromInventory(String pProductName,Integer quantity){
        if (!Inventory.containsKey(pProductName))
        {
            System.out.println("Product "+pProductName+" not found");
        }
        Integer oldQuantity = Inventory.get(pProductName);
        oldQuantity -= quantity;
        if (oldQuantity < 0) {
            System.out.println("Product "+pProductName+"does not fulfill Quantity");
        }
        else if(oldQuantity == 0) {
            Inventory.remove(pProductName);
        }
        else
        {
            Inventory.put(pProductName,oldQuantity);
        }
    }
    public void  printInventory(){
        System.out.println("INVENTORY: ");
        Inventory.forEach((key, value) -> {
            System.out.println(key+" "+value);
        });
    }
    public boolean removeProducts(Map<String,Integer> toRemoveProducts){
        if(toRemoveProducts.isEmpty())
        {
            System.out.println("No products to remove");
        }
        for(Map.Entry<String,Integer> entry : toRemoveProducts.entrySet())
        {
            removeProductFromInventory(entry.getKey(),entry.getValue());
        }
        return true;
    }
    public Integer getStock(String pProductName){
        if(!Inventory.containsKey(pProductName))
        {
             System.out.println("Product "+pProductName+" not found in inventory");
        }
        return Inventory.get(pProductName);
    }
}