package com.anuraj.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anuraj.productservice.entity.Product;


public interface ProductRepository extends JpaRepository<Product,Integer>{

}
