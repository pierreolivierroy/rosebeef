package ca.etsmtl.gti710.providers;

import java.util.ArrayList;

import ca.etsmtl.gti710.models.Product;

public interface IProvider {
	
	public ArrayList<Product> getProducts();
	public Product getProduct(int id);
	
}
