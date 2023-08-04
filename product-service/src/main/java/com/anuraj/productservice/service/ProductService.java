package com.anuraj.productservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anuraj.productservice.dto.ProductDto;
import com.anuraj.productservice.entity.Product;
import com.anuraj.productservice.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	public ProductDto createProduct(ProductDto productDto) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		
		Product newProduct = productRepo.save(product);
		
		return producttoDto(newProduct);
		
		
	}
	
	public ProductDto producttoDto(Product product) {
		ProductDto prodDto = mapper.map(product, ProductDto.class);
		return prodDto;
	}

	public List<ProductDto> getProducts() {
		// TODO Auto-generated method stub
		
		List<Product> products = productRepo.findAll();
		List<ProductDto> productDtos = products.stream().map(p->producttoDto(p)).collect(Collectors.toList());
		return productDtos;
	}

}
