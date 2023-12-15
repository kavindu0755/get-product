package com.restapiwithmongodb.getproduct.controller;

import com.restapiwithmongodb.getproduct.models.entities.Product;
import com.restapiwithmongodb.getproduct.service.ProductsFetchingService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/products")
public class ProductController {

    @Autowired
    private ProductsFetchingService fetchingService;

    // CREATE
    @PostMapping
    public ResponseEntity<Product> createNewProduct(@RequestBody Product product) {
        Product newProduct = fetchingService.createProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProductList() {
        return new ResponseEntity<List<Product>>(fetchingService.findAllProducts(), HttpStatus.OK);
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable ObjectId productId) {
        Optional<Product> product = fetchingService.getSingleProductById(productId);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{productId}")
    public void updateProduct(@PathVariable ObjectId productId, @RequestBody Product product) {
        fetchingService.updateProduct(productId, product);
    }

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable ObjectId productId) {
        Product deleteProduct = fetchingService.deleteProductById(productId);
        return ResponseEntity.ok(deleteProduct);
    }
}
