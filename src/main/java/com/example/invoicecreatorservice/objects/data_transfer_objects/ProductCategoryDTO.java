package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.ProductCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCategoryDTO {
    private int id;
    private String name;
    private int btw;
    private int companyId;

    public ProductCategoryDTO(ProductCategory productCategory){
        this.id = productCategory.getId();
        this.name = productCategory.getName();
        this.btw = productCategory.getBtw();
        this.companyId = productCategory.getCompanyId();
    }

    public ProductCategoryDTO(){}

}
