package ca.etsmtl.gti710.providers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ca.etsmtl.gti710.models.Product;
import ca.etsmtl.gti710.openErp.ClientAPI;

public class OpenERPProvider implements IProvider {

	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:OpenERP-context.xml");
	ClientAPI client = (ClientAPI) context.getBean("client");

	@Override
	public Product getProduct(int id) {
	    client.connect();
	    
	    HashMap<String, Object> products = client.readProduct(id);

	    Product p = new Product(id);
	    
	    for(Map.Entry<String, Object> entry : products.entrySet()) {
	      	
	    	if (entry.getKey().equals("name")) {
	    		p.setName(entry.getValue().toString());
	    	} else if (entry.getKey().equals("qty_available")) {
	    		p.setQuantity((Double)entry.getValue());
	    	} else if (entry.getKey().equals("description")) {
	    		p.setDescription(entry.getValue().toString());
	    	} else if (entry.getKey().equals("lst_price")) {
	    		p.setPrice((Double)entry.getValue());
	    	} else if (entry.getKey().equals("default_code")) {
	    		p.setCode(entry.getValue().toString());
	    	}
	    }
	    
	    return p;
	}

	@Override
	public ArrayList<Product> getProducts() {
		// TODO Auto-generated method stub
		return null;
	}

}
