package api.service.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@SuppressWarnings("WeakerAccess")
@XmlRootElement
public class Product {
    private int id;
    private String name;
    private int price;
    private int categoryId;
    private String productCode;

    public Product(int id, String name, float price, int categoryId, String productCode){
        this.id = id;
        this.name = name;
        this.price = (int)Math.round(price * 100.0);
        this.categoryId = categoryId;
        this.productCode = productCode;
    }

    public Product(){

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String Name){
        this.name = name;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public int getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(int categoryId){
        this.categoryId = categoryId;
    }

    public String getProductCode(){
        return productCode;
    }

    public void setProductCode(String productCode){
        this.productCode = productCode;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return this.id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name +
                ", price=" + price +
                ", categoryId=" + categoryId +
                ", productCode=" + productCode +
                '}';
    }

}
