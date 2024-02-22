package com.matus.springboot.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matus.springboot.models.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, UUID>{

}
