package com.matus.springboot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.matus.springboot.dto.ProductRecordDto;
import com.matus.springboot.models.ProductModel;
import com.matus.springboot.repositories.ProductRepository;

import jakarta.validation.Valid;

@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;
	
	@PostMapping("/products")
	public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto ){
		var productModel = new ProductModel();
		BeanUtils.copyProperties(productRecordDto, productModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductModel>> getAllProducts(){
		return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Object> getProductOne(@PathVariable(value = "id")UUID id){
		Optional<ProductModel> product0 = productRepository.findById(id);
		if(product0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(product0.get());
	}
}
