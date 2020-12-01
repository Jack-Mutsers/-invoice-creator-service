package com.example.invoicecreatorservice.objects.models;

import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductCategoryForAlterationDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@SuppressWarnings("WeakerAccess")
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Getter
@Setter
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true, nullable = false)
    private String name;
    private int btw;
    private int companyId;

    public ProductCategory(int id, String name, int btw, int companyId){
        this.id = id;
        this.name = name;
        this.btw = btw;
        this.companyId = companyId;
    }

    public ProductCategory(ProductCategoryForAlterationDTO productDTO){
        this.id = productDTO.getId();
        this.name = productDTO.getName();
        this.btw = productDTO.getBtw();
        this.companyId = productDTO.getCompanyId();
    }

    public ProductCategory(){}

}
