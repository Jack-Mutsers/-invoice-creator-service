package com.example.invoicecreatorservice.objects.data_transfer_objects;

import lombok.Getter;

import static com.example.invoicecreatorservice.helpers.tools.Helper.validateStringValue;

@Getter
public class ProductCategoryForAlterationDTO {
    private int id;
    private String name;
    private int btw;
    private int companyId;

    public ProductCategoryForAlterationDTO(int id, String name, int btw, int companyId){
        this.id = id;
        this.name = name;
        this.btw = btw;
        this.companyId = companyId;
    }

    public ProductCategoryForAlterationDTO(){}

    public boolean validateForUpdate(){
        return ( this.id == 0 || this.validateForCreation() );
    }

    public boolean validateForCreation(){
        return ( validateStringValue(this.name) || this.btw == 0 || this.companyId == 0 );
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

}
