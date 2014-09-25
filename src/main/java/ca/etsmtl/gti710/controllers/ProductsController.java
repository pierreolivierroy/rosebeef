package ca.etsmtl.gti710.controllers;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.etsmtl.gti710.models.Product;
import ca.etsmtl.gti710.providers.IProvider;
import ca.etsmtl.gti710.providers.StubProvider;

@RestController

public class ProductsController {
	
	IProvider provider = new StubProvider();
	
	@RequestMapping("/products")
	public ArrayList<Product> products() {
		return provider.getProducts();
	}
	
	@RequestMapping("/products/{id}")
	public Product product(@PathVariable int id) {
		return provider.getProduct(id);
	}
}