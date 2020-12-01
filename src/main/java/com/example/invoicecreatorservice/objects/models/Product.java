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

    private String productCode;
    private int categoryId;
    private int companyId;

    public Product(int id, String name, double price, int categoryId, String productCode, int companyId){
        this.id = id;
        this.name = name;
        this.price = (int)Math.round(price * 100.0);
        this.categoryId = categoryId;
        this.productCode = productCode;
        this.categoryId = categoryId;
        this.companyId = companyId;
    }

    public Product(ProductForAlterationDTO productDTO){
        this.id = productDTO.getId();
        this.name = productDTO.getName();
        this.price = productDTO.getPrice();
        this.categoryId = productDTO.getCategoryId();
        this.productCode = productDTO.getProductCode();
        this.companyId = productDTO.getCompanyId();
    }

    public Product(){}

    public double getPrice(){
        return price / 100.0;
    }

    public void setPrice(double price){
        this.price = (int)Math.round(price * 100.0);
    }

}
