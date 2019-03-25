package fr.ulille.iut.chatisfait.dto;

import java.sql.Date;

public class AbonnementDto {

    private int idAbonnement;
    private int idutilisateur;
    private int idArticle;
    private int age;
    private String poil;
    private boolean sterilise;
    private double poids;
    private String nom;
    private int choix;

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPoil() {
        return poil;
    }

    public void setPoil(String poil) {
        this.poil = poil;
    }

    public boolean isSterilise() {
        return sterilise;
    }

    public void setSterilise(boolean sterilise) {
        this.sterilise = sterilise;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdAbonnement() {
        return idAbonnement;
    }

    public void setIdAbonnement(int idAbonnement) {
        this.idAbonnement = idAbonnement;
    }

    public int getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public int getChoix() {
        return choix;
    }

    public void setChoix(int choix) {
        this.choix = choix;
    }
}
