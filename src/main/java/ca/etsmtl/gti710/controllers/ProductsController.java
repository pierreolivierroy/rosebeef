package ca.etsmtl.gti710.controllers;
import java.util.ArrayList;
import java.util.HashMap;

import ca.etsmtl.gti710.models.Product;

import ca.etsmtl.gti710.openErp.ClientAPI;
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
		
		ClientAPI client = (ClientAPI) context.getBean("client");
        client.connect();
        HashMap<Integer, Object> result = client.read();
        System.out.println(result);
		
		return provider.getProducts();
	}
	
}