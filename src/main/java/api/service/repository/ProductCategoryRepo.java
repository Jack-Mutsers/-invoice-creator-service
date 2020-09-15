package api.service.repository;

import api.service.model.Customer;
import api.service.model.Product;
import api.service.model.ProductCategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductCategoryRepo {
    private final List<ProductCategory> categoryList = new ArrayList<>();

    public ProductCategoryRepo(){
        categoryList.add(new ProductCategory(1, "electronics", 21));
        categoryList.add(new ProductCategory(2, "schoonmaak spullen", 21));
        categoryList.add(new ProductCategory(3, "kleren", 21));
        categoryList.add(new ProductCategory(4, "Eet en dink wares", 6));
    }

    public List<ProductCategory> getCategories() {
        return categoryList;
    }

    public List<ProductCategory> getCategoriesByBtw(int btw) {
        List<ProductCategory> filetered = new ArrayList<>();
        for (ProductCategory category : categoryList) {
            if (category.getBtw() == btw) {
                filetered.add(category);
            }
        }
        return filetered;
    }

    public ProductCategory getCategory(int id) {
        for (ProductCategory category : categoryList) {
            if (category.getId() == id)
                return category;
        }
        return null;
    }

    public boolean delete(int id) {
        ProductCategory category = getCategory(id);
        if (category == null){
            return false;
        }

        return categoryList.remove(category);
    }


    public boolean add(ProductCategory category) {
        if(category.getId() == 0){
            int oldId = 0;
            for (ProductCategory category1 : categoryList){
                int newId = category.getId();
                if(oldId < newId){
                    oldId = newId;
                }
            }

            category = new ProductCategory(
                    (oldId+1),
                    category.getName(),
                    category.getBtw()
            );
        }

        if (this.getCategory(category.getId()) != null){
            return false;
        }
        categoryList.add(category);
        return true;
    }

    public boolean update(int id, ProductCategory category) {
        ProductCategory old = this.getCategory(id);
        if (old == null) {
            return false;
        }

        old.setName(category.getName());
        old.setBtw(category.getBtw());

        return true;
    }
}
