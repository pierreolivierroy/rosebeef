package ca.etsmtl.gti710.providers;

import java.util.ArrayList;

import ca.etsmtl.gti710.models.Order;
import ca.etsmtl.gti710.models.Product;

public class StubProvider implements IProvider {
	
	private ArrayList<Product> produits = new ArrayList<Product>();
	
	public StubProvider() {
		if (produits.size() == 0)
		{
			for (int i = 1 ; i <= 5 ; i++)
			{
				Product p = new Product(i);
				p.setName("test");
				produits.add(p);
			}
		}		
	}

	@Override
	public ArrayList<Product> getProducts() {
		return produits;
	}

	@Override
	public Product getProduct(int id) {
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
