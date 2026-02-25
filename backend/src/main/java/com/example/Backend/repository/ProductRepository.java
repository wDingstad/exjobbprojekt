package com.example.Backend.repository;

import com.example.Backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    Product findByArticleNumber(Integer articleNumber);





}
