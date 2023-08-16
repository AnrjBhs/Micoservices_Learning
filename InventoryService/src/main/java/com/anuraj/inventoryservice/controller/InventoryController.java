package com.anuraj.inventoryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anuraj.inventoryservice.dto.InventoryResponse;
import com.anuraj.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;

	@GetMapping
	public ResponseEntity<List<InventoryResponse>> isInStock(@RequestParam List<String>  skuCode){
		
		List<InventoryResponse> stock = inventoryService.isInStock(skuCode);
		
		return new ResponseEntity<List<InventoryResponse>>(stock,HttpStatus.OK);
	}
}
