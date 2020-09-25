package api.service.datatransferobjects;

import api.service.model.Product;
import api.service.model.ProductCategory;
import api.service.repository.ProductCategoryRepo;

public class ProductDTO {
    private int id;
    private String name;
    private int price;
    private ProductCategory category;
    private String productCode;

    public ProductDTO(int id, String name, float price, int categoryId, String productCode){
        ProductCategoryRepo category = new ProductCategoryRepo();

        this.id = id;
        this.name = name;
        this.price = (int)Math.round(price * 100.0);
        this.category = category.getCategory(categoryId);
        this.productCode = productCode;
    }

    public ProductDTO(Product product){
        ProductCategoryRepo category = new ProductCategoryRepo();

        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.category = category.getCategory(product.getCategoryId());
        this.productCode = product.getProductCode();
    }

    public ProductDTO(){

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

    public ProductCategory getCategoryId(){
        return category;
    }

    public void setCategoryId(ProductCategory category){
        this.category = category;
    }

    public String getProductCode(){
        return productCode;
    }

    public void setProductCode(String productCode){
        this.productCode = productCode;
    }
}
