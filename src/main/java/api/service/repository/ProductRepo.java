package api.service.repository;

import api.service.model.Customer;
import api.service.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductRepo {
    private final List<Product> productList = new ArrayList<>();

    public ProductRepo(){
        productList.add(new Product(1, "rekenmashine", 1000, 1, "ELCT0001"));
        productList.add(new Product(2, "T-Shirt", 1650, 3, "CLTH0001"));
        productList.add(new Product(3, "tandenpasta", 675, 2, "CLNG0001"));
        productList.add(new Product(4, "appel", 125, 4, "FDDK0001"));
        productList.add(new Product(5, "computer onderhoud", 750, 4, "PSAC0001"));
    }

    public List<Product> getProducts() {
        return productList;
    }

    public List<Product> getProductByCategory(int cat) {
        List<Product> filetered = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCategoryId() == cat) {
                filetered.add(product);
            }
        }
        return filetered;
    }

    public Product getProduct(int id) {
        for (Product product : productList) {
            if (product.getId() == id)
                return product;
        }
        return null;
    }

    public boolean delete(int id) {
        Product product = getProduct(id);
        if (product == null){
            return false;
        }

        return productList.remove(product);
    }

    public boolean add(Product product) {
        if(product.getId() == 0){
            int oldId = 0;
            for (Product product1 : productList){
                int newId = product1.getId();
                if(oldId < newId){
                    oldId = newId;
                }
            }

            product = new Product(
                    (oldId+1),
                    product.getName(),
                    product.getPrice(),
                    product.getCategoryId(),
                    product.getProductCode()
            );
        }

        if (this.getProduct(product.getId()) != null){
            return false;
        }
        productList.add(product);
        return true;
    }

    public boolean update(int id, Product product) {
        Product old = this.getProduct(id);
        if (old == null) {
            return false;
        }

        old.setName(product.getName());
        old.setPrice(product.getPrice());
        old.setCategoryId(product.getCategoryId());
        old.setProductCode(product.getProductCode());

        return true;
    }
}
