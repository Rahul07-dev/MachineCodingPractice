package  models;
public class Product {
    private String productName;
    private String info;
    public Product(String productName, String info) {
        this.info=info;
        this.productName=productName;
    }

    public String getInfo() {
        return info;
    }

    public String getProductName() {
        return productName;
    }
}
