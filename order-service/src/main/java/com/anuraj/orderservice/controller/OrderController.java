package com.anuraj.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anuraj.orderservice.dto.OrderDto;
import com.anuraj.orderservice.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/place")
	public ResponseEntity<String> placeOrder(@RequestBody OrderDto order){
		String response = orderService.createOrder(order);
		
		return new ResponseEntity<String>(response,HttpStatus.OK);
		
	}

}
