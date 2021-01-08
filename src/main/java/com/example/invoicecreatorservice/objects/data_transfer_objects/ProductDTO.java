package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductDTO {
    private int id;
    private String name;
    private double price;
    private int categoryId;
    private ProductCategoryDTO category;
    private String productCode;

    public ProductDTO(Product product){

        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.categoryId = product.getCategoryId();
        this.productCode = product.getProductCode();
    }

    public ProductDTO(){
        this.price = 0.0;
    }

    public List<ProductDTO> getProductList(List<Product> products){
        List<ProductDTO> productList = new ArrayList<>();
        for (Product product : products)
        {
            productList.add( new ProductDTO(product) );
        }

        return productList;
    }

}
