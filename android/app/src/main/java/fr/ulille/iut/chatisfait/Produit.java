package fr.ulille.iut.chatisfait;

public class Produit {

    private String category;
    private String nom;
    private String prix;

    public Produit(String cat, String name, String price){
        this.category = cat;
        this.nom = name;
        this.prix = price;
    }

    public double getPrice(){
        return Double.parseDouble(prix.substring(0, prix.length()-1));
    }

    public String toString(){
        return category + " , " + nom + " , " + prix;
    }
}
