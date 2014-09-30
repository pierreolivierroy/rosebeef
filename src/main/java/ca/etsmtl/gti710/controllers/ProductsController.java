package ca.etsmtl.gti710.controllers;
import java.util.ArrayList;

import ca.etsmtl.gti710.models.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.etsmtl.gti710.providers.IProvider;
import ca.etsmtl.gti710.providers.StubProvider;

@RestController
public class ProductsController {
	
	IProvider provider = new StubProvider();
	
	@RequestMapping("/products")
	public ArrayList<Product> products() {
		return provider.getProducts();
	}
	
}