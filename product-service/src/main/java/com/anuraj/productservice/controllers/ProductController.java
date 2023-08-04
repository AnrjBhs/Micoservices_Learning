package com.anuraj.productservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anuraj.productservice.dto.ProductDto;
import com.anuraj.productservice.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService prodService;
	

	@PostMapping("/create")
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product){
		ProductDto productDto = prodService.createProduct(product);
		return new ResponseEntity<ProductDto>(productDto,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<ProductDto>> getProducts(){
		List<ProductDto> products = prodService.getProducts();
		
		return new ResponseEntity<List<ProductDto>>(products,HttpStatus.OK);
	}
}
