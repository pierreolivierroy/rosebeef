package ca.etsmtl.gti710.providers;

import java.util.ArrayList;

import ca.etsmtl.gti710.models.Produit;

public class StubProvider implements IProvider {
	
	private ArrayList<Produit> produits = new ArrayList<Produit>();

	@Override
	public ArrayList<Produit> getProducts() {
		
		if (produits.size() == 0)
		{
			for (int i = 1 ; i <= 5 ; i++)
			{
				Produit p = new Produit(i);
				p.setName("test");
				produits.add(p);
			}
		}
		
		return produits;
	}

	@Override
	public Produit getProduct(int id) {
		return produits.get(id);
	}

}
