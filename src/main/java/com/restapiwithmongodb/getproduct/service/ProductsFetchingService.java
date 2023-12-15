package com.restapiwithmongodb.getproduct.service;

import com.restapiwithmongodb.getproduct.dao.repository.ProductRepository;
import com.restapiwithmongodb.getproduct.exceptions.ProductNotFoundException;
import com.restapiwithmongodb.getproduct.models.entities.Product;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsFetchingService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductsFetchingService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getSingleProductById(ObjectId id) {
        return productRepository.findById(id).filter(product -> product.getProductId().equals(id));
    }

    public void updateProduct(ObjectId id, Product updateProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) {
            Product updateThisProduct = existingProduct.get();
            updateThisProduct.setProductCategory(updateProduct.getProductCategory());
            updateThisProduct.setProductName(updateProduct.getProductName());
            updateThisProduct.setPrice(updateProduct.getPrice());
            updateThisProduct.setDescription(updateProduct.getDescription());
            updateThisProduct.setAvailable(updateProduct.isAvailable());

            productRepository.save(updateThisProduct);
        } else {
            throw new ProductNotFoundException("Product not found for product update this id " + id);
        }

    }

    public Product deleteProductById(ObjectId id) {
        productRepository.deleteById(id);
        return null;
    }

}
