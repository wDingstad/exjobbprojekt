package com.example.Backend.service;

import com.example.Backend.models.Product;
import com.example.Backend.exception.ResourceNotFoundException;
import com.example.Backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){

        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductByArticleNumber(Integer articleNumber) {
        return productRepository.findByArticleNumber(articleNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with article number: " + articleNumber));
    }

    public Product getProductById(Integer productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public Product createProduct(Integer articleNumber, String name, String description, BigDecimal price, String supplier){
        Product newProduct = new Product(articleNumber, name, description, price, supplier);
        return productRepository.save(newProduct);

    }

    public Product updateProduct(Integer id, Product updatedProduct){
        Product foundProduct = getProductById(id);

        foundProduct.setName(updatedProduct.getName());
        foundProduct.setArticleNumber(updatedProduct.getArticleNumber());
        foundProduct.setDescription(updatedProduct.getDescription());
        foundProduct.setSupplier(updatedProduct.getSupplier());
        foundProduct.setPrice(updatedProduct.getPrice());

        return productRepository.save(foundProduct);

    }

    public void deleteProduct(Integer id){
        productRepository.deleteById(id);
    }








}
