package ca.etsmtl.gti710.controllers;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ca.etsmtl.gti710.exceptions.OrderNotFoundException;
import ca.etsmtl.gti710.models.Order;
import ca.etsmtl.gti710.providers.IProvider;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
public class OrdersController {

	@Autowired
	IProvider provider;

	@RequestMapping(value="/orders", method=RequestMethod.GET)
	public ArrayList<Order> orders() {
		return provider.getOrders();
	}

    @RequestMapping(value="/orders", method=RequestMethod.POST, headers = {"Content-type=application/json"})
	public int createOrders(@RequestBody Order order) {
		try {
            return provider.createOrder(order);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.NOT_MODIFIED);
        }
	}
	
	@RequestMapping("/orders/{order_id}")
	public Order order(@PathVariable("order_id") int order_id) {
		try {
			Order order =  provider.getOrder(order_id);
            if (order == null){
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
            }
            else{
                return order;
            }
		} catch (NullPointerException e) {
            throw new HttpClientErrorException(HttpStatus.CONFLICT);
		}
	}
}