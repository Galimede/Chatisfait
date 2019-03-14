package fr.ulille.iut.pizzaland.dto;

public class PizzaShortDto {
	private long id;
	private String nom;
	private String base;
	private float prix_petite;
	private float prix_grande;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public float getPrix_petite() {
		return prix_petite;
	}

	public void setPrix_petite(float prix_petite) {
		this.prix_petite = prix_petite;
	}

	public float getPrix_grande() {
		return prix_grande;
	}

	public void setPrix_grande(float prix_grande) {
		this.prix_grande = prix_grande;
	}
}