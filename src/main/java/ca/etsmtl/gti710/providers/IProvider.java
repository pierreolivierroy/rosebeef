package ca.etsmtl.gti710.providers;

import java.util.ArrayList;

import ca.etsmtl.gti710.models.Produit;

public interface IProvider {
	
	public ArrayList<Produit> getProducts();
	public Produit getProduct(int id);
	
}
