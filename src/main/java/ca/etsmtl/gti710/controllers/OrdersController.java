package ca.etsmtl.gti710.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ca.etsmtl.gti710.exceptions.OrderNotFoundException;
import ca.etsmtl.gti710.models.Order;
import ca.etsmtl.gti710.providers.IProvider;

@RestController
public class OrdersController {
	
	@Autowired
	IProvider provider;

	@RequestMapping("/orders")
	public ArrayList<Order> orders() {		
		return provider.getOrders();
	}

	@RequestMapping(value="/orders", method=RequestMethod.POST)
	public Order createOrders() {		
		return provider.createOrder();
	}
	
	@RequestMapping("/orders/{order_id}")
	public Order order(@PathVariable("order_id") int order_id) {
		try {
			return provider.getOrder(order_id);
		} catch (NullPointerException e) {
			throw new OrderNotFoundException();
		}
	}
}