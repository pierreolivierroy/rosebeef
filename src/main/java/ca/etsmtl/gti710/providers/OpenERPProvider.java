package ca.etsmtl.gti710.providers;

import java.util.ArrayList;
import java.util.HashMap;

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
 
	    HashMap<String, Object> openERPProduct = client.readProduct(id);

	    Product p = new Product(id);
	    p.setName(openERPProduct.get("name").toString());
	    p.setQuantity((Double)openERPProduct.get("qty_available"));
	    p.setDescription(openERPProduct.get("description").toString());
	    p.setPrice((Double)openERPProduct.get("lst_price"));

	    boolean code = Boolean.parseBoolean(openERPProduct.get("default_code").toString());

	    if (!code) {
	    	p.setCode(openERPProduct.get("default_code").toString());
	    }

	    return p;
	}

	@Override
	public ArrayList<Product> getProducts() {
		// TODO Auto-generated method stub
		return null;
	}

}
