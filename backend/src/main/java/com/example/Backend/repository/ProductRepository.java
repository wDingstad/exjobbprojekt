package com.example.Backend.repository;

import com.example.Backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    Optional<Product> findByArticleNumber(Integer articleNumber);





}
