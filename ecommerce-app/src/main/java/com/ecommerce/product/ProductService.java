package com.ecommerce.product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private ProductRepository repo = new ProductRepository();

    public List<Product> getProducts(String category, String search) {
        try {
            return repo.findAll(category, search);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
