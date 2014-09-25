package ca.etsmtl.gti710.models;

public class Produit {
	
	private int id;
	private String name;
	private String description;
	
	public Produit(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
