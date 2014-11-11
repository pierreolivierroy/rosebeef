package ca.etsmtl.gti710.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
	public Order createOrders(@RequestParam(value="customer_id", required=true) int custormer_id) {
		try {
            return provider.createOrder(custormer_id);
        } catch (Exception e) {
            return null;
        }
	}

    @RequestMapping(value="/orders/{order_id}/lineOrder", method=RequestMethod.POST)
    public Order addLineOrder(@PathVariable("order_id") int order_id, @RequestParam(value="product_id", required=true) int product_id, @RequestParam(value="quantity", required=true) int quantity) {
        try {
            return provider.addLineOrder(order_id, product_id, quantity);
        } catch (Exception e) {
            return null;
        }
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