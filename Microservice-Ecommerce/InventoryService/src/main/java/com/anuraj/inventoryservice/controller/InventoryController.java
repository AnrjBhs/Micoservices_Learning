package com.anuraj.inventoryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anuraj.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;

	@GetMapping("/{sku-Code}")
	public ResponseEntity<Boolean> isInStock(@PathVariable("sku-Code") String skuCode){
		
		Boolean stock = inventoryService.isInStock(skuCode);
		
		return new ResponseEntity<Boolean>(stock,HttpStatus.OK);
	}
}
