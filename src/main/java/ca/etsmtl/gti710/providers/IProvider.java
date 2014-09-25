package ca.etsmtl.gti710.providers;

import java.util.Collection;

import ca.etsmtl.gti710.models.Produit;

public interface IProvider {
	
	public Collection<Produit> getProducts();
	public Produit getProduct(int id);
	
}
