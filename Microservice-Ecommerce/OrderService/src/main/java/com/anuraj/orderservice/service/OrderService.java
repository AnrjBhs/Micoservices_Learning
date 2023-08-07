package com.anuraj.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public String createOrder(OrderDto orderDto) {
		 Order order = new Order();
	     order.setOrderNumber(UUID.randomUUID().toString());

	     List<OrderLineItems> orderLineItems = orderDto.getOrderLineItemsDtoList()
	                .stream()
	                .map(this::mapToDto)
	                .toList();

	        order.setOrderLineItemsList(orderLineItems);

	       
	        orderRepo.save(order);
	        
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
