package fr.ulille.iut.chatisfait;

public class Produit {

    private String category;
    private String nom;
    private String prix;
    private int id;

    public Produit(String cat, String name, String price, int id){
        this.category = cat;
        this.nom = name;
        this.prix = price;
        this.id = id;
    }

    public double getPrice(){
        return Double.parseDouble(prix.substring(0, prix.length()-1));
    }

    public int getId(){
        return id;
    }

    public String toString(){
        return category + " , " + nom + " , " + prix;
    }
}
