package com.example.invoicecreatorservice.objects.models;

import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductForAlterationDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@SuppressWarnings("WeakerAccess")
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int price;

    @Column(unique=true, nullable = false)
    private String productCode;
    private int categoryId;

    public Product(int id, String name, double price, int categoryId, String productCode){
        this.id = id;
        this.name = name;
        this.price = (int)Math.round(price * 100.0);
        this.categoryId = categoryId;
        this.productCode = productCode;
    }

    public Product(ProductForAlterationDTO productDTO){
        this.id = productDTO.getId();
        this.name = productDTO.getName();
        this.price = productDTO.getPrice();
        this.categoryId = productDTO.getCategoryId();
        this.productCode = productDTO.getProductCode();
    }

    public Product(){}

    public double getPrice(){
        return price / 100.0;
    }

    public void setPrice(double price){
        this.price = (int)Math.round(price * 100.0);
    }

}
