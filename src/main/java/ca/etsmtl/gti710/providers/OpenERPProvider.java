package ca.etsmtl.gti710.providers;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ca.etsmtl.gti710.models.Order;
import ca.etsmtl.gti710.models.Product;
import ca.etsmtl.gti710.openErp.ClientAPI;

public class OpenERPProvider implements IProvider {

	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:OpenERP-context.xml");
	ClientAPI client = (ClientAPI) context.getBean("client");

	@Override
	public Product getProduct(int id) {

		client.connect();
        System.out.println("ALLO ******************** ASDBAKJDHAD");
        client.createClient("Olivier", "Rivard", "92 chénier app. 201", "St-Philippe", "J0L 2K0", "514 608-6253", "", "");
	    HashMap<String, Object> openERPProduct = client.readProduct(id);

	    Product p = new Product(id);
	    p.setName(openERPProduct.get("name").toString());
	    p.setQuantity((Double)openERPProduct.get("qty_available"));
	    p.setPrice((Double)openERPProduct.get("lst_price"));

	    if (!openERPProduct.get("description").toString().equals("false")) {
	    	p.setDescription(openERPProduct.get("description").toString());
	    }

	    if (!openERPProduct.get("default_code").toString().equals("false")) {
	    	p.setCode(openERPProduct.get("default_code").toString());
	    }

	    return p;
	}

	@Override
	public ArrayList<Product> getProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Order> getOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOrder(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
