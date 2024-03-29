package fr.ulille.iut.chatisfait.dto;

/**
 * UtilisateurDto
 *
 * @author blochs
 */
public class UtilisateurDto {
    private int idUtilisateur;
    private String pseudo;
    private String mdp;
    private String sel;
    private String prenom;
    private String nom;
    private String adresse;
    private String adresseMail;
    private boolean abonne;
    private boolean admin;

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public String getSel() {
        return sel;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setSel(String sel) {
        this.sel = sel;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public boolean isAbonne() {
        return abonne;
    }

    public void setAbonne(boolean abonne) {
        this.abonne = abonne;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
