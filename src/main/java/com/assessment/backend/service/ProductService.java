package com.assessment.backend.service;

import com.assessment.backend.entities.Product;
import com.assessment.backend.persistance.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }
    public Product getProductById(Long id) {
        return repository.findById(id).orElse(null);
    }
    public Product saveProduct(Product product) {
        return repository.save(product);
    }
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
    public List<Product> searchProducts(String name) {
        return repository.searchByName(name);
    }

    public double getAveragePrice() {
        return repository.findAll().stream().mapToDouble(Product::getPrice).average().orElse(0.0);
    }


    public Product updateProduct(Long id, Product product) {
        Product existingProduct = repository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            return repository.save(existingProduct);
        }
        return null; // or throw a custom NotFoundException
    }

    public List<Product> filterProducts(String name, Double minPrice, Double maxPrice) {
        List<Product> products = repository.findAll();

        if (name != null && !name.isEmpty()) {
            products = products.stream()
                    .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }

        if (minPrice != null) {
            products = products.stream()
                    .filter(p -> p.getPrice() >= minPrice)
                    .toList();
        }

        if (maxPrice != null) {
            products = products.stream()
                    .filter(p -> p.getPrice() <= maxPrice)
                    .toList();
        }

        return products;
    }

}