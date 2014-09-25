package ca.etsmtl.gti710.providers;

import java.util.ArrayList;

import ca.etsmtl.gti710.models.Product;

public class StubProvider implements IProvider {
	
	private ArrayList<Product> produits = new ArrayList<Product>();

	@Override
	public ArrayList<Product> getProducts() {
		
		if (produits.size() == 0)
		{
			for (int i = 1 ; i <= 5 ; i++)
			{
				Product p = new Product(i);
				p.setName("test");
				produits.add(p);
			}
		}
		
		return produits;
	}

	@Override
	public Product getProduct(int id) {
		return produits.get(id);
	}

}
