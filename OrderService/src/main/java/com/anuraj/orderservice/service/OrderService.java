package com.anuraj.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.anuraj.orderservice.dto.InventoryResponse;
import com.anuraj.orderservice.dto.OrderDto;
import com.anuraj.orderservice.dto.OrderLineItemsDto;
import com.anuraj.orderservice.entity.Order;
import com.anuraj.orderservice.entity.OrderLineItems;
import com.anuraj.orderservice.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private  WebClient webClient;
	
	public String createOrder(OrderDto orderDto) {
		 Order order = new Order();
	     order.setOrderNumber(UUID.randomUUID().toString());

	     List<OrderLineItems> orderLineItems = orderDto.getOrderLineItemsDtoList()
	                .stream()
	                .map(this::mapToDto)
	                .toList();

	        order.setOrderLineItemsList(orderLineItems);
	        
	        // getting all skuCodes from order
	        
	      List<String> skuCodes = orderLineItems.stream().map(orderLineItem -> orderLineItem.getSkuCode()).toList();
	        // call inventory service and check whether the items in order are available or not
	        
	      InventoryResponse[] inventoryResponseArray =  webClient.get().
	        uri("http://localhost:8083/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build()).
	        retrieve().
	        bodyToMono(InventoryResponse[].class).
	        block();
	        
	      boolean allProductsInStock = true;
	      for(var inventoryResponse : inventoryResponseArray) {
	    	  if(!inventoryResponse.getIsInStock()) {
	    		  allProductsInStock = false;
	    		  break;
	    	  }
	      }
	     
	     if(allProductsInStock) {
		        orderRepo.save(order);

	     }else {
	    	 throw new IllegalArgumentException("Product is not in stock, Please try again later");
	     }

	       
	        
	        return "Order Placed";
	        
	        
	}
	
	 private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
	        OrderLineItems orderLineItems = new OrderLineItems();
	        orderLineItems.setPrice(orderLineItemsDto.getPrice());
	        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
	        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
	        return orderLineItems;
	    }
}
