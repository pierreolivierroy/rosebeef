package ca.etsmtl.gti710.controllers;
import java.util.ArrayList;

import ca.etsmtl.gti710.models.Product;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.etsmtl.gti710.openErp.Connection;
import ca.etsmtl.gti710.providers.IProvider;
import ca.etsmtl.gti710.providers.StubProvider;

@RestController
public class ProductsController {
	
	IProvider provider = new StubProvider();
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:OpenERP-context.xml");
	
	@RequestMapping("/products")
	public ArrayList<Product> products() {
		
		Connection connect = (Connection) context.getBean("connection");
		System.out.println(connect.connect());
		
		return provider.getProducts();
	}
	
}