package com.example.Backend.controller;


import com.example.Backend.models.Product;
import com.example.Backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {


    private final ProductService productService;


    public ProductController(ProductService  productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/products/{articleNumber}")
    public ResponseEntity<Product> findByArticleNumber(@PathVariable Integer articleNumber){
        Product foundProduct = productService.getProductByArticleNumber(articleNumber);
        return ResponseEntity.ok(foundProduct);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product newProduct){
        Product savedProduct = productService.createProduct(newProduct.getArticleNumber(),
                newProduct.getName(),
                newProduct.getDescription(),
                newProduct.getPrice(),
                newProduct.getSupplier());
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product updateproduct) {
        Product updated = productService.updateProduct(id, updateproduct);
        return ResponseEntity.ok(updated);

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){

        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }




    }
