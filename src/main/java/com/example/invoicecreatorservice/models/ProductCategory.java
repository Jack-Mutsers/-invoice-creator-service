package com.example.invoicecreatorservice.models;

import com.example.invoicecreatorservice.data_transfer_objects.ProductCategoryForCreationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.ProductCategoryForUpdateDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("WeakerAccess")
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int btw;

    public ProductCategory(int id, String name, int btw){
        this.id = id;
        this.name = name;
        this.btw = btw;
    }

    public ProductCategory(ProductCategoryForCreationDTO productDTO){
        this.id = 0;
        this.name = productDTO.getName();
        this.btw = productDTO.getBtw();
    }

    public ProductCategory(ProductCategoryForUpdateDTO productDTO){
        this.id = productDTO.getId();
        this.name = productDTO.getName();
        this.btw = productDTO.getBtw();
    }

    public ProductCategory(){

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

    public void setName(String name){
        this.name = name;
    }

    public int getBtw(){
        return btw;
    }

    public void setBtw(int btw){
        this.btw = btw;
    }
}
