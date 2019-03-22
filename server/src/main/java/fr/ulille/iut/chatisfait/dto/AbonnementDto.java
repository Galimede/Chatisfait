package fr.ulille.iut.chatisfait.dto;

import java.sql.Date;

public class AbonnementDto {

    private int idAbonnement;
    private int idutilisateur;
    private int idArticle;
    private String typeAbonne;


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

    public String getTypeAbonne() {
        return typeAbonne;
    }

    public void setTypeAbonne(String type) {
        this.typeAbonne = type;
    }
}
