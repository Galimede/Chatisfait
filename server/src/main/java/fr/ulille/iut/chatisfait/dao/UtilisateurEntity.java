package fr.ulille.iut.chatisfait.dao;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * UtilisateurEntity
 *
 * @author blochs
 */
@Entity
@Table(name = "utilisateur")

@NamedQueries({
        @NamedQuery(name = "FindAllUtilisateurs", query = "SELECT u from UtilisateurEntity u"),
        //@NamedQuery(name = "FindIngredientByName", query = "SELECT u from UtilisateurEntity u where u.pseudo = :upseudo")
})

public class UtilisateurEntity {
    private int idUtilisateur;
    private String pseudo;
    private String mdp;
    private String sel;
    private String prenom;
    private String nom;
    private String adresse;
    private String adresseMail;

    private final static Logger logger = LoggerFactory.getLogger(UtilisateurEntity.class);
    private static ModelMapper modelMapper = new ModelMapper();

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idutilisateur", nullable = false)
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idutilisateur) {
        this.idUtilisateur = idutilisateur;
    }
    @Basic
    @Column(name = "nom", nullable = false, length = -1)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "pseudo", nullable = false, length = -1)
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    @Basic
    @Column(name = "mdp", nullable = false, length = -1)
    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Basic
    @Column(name = "sel", nullable = false, length = -1)
    public String getSel() {
        return sel;
    }

    public void setSel(String sel) {
        this.sel = sel;
    }
    @Basic
    @Column(name = "prenom", nullable = false, length = -1)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    @Basic
    @Column(name = "adresse", nullable = false, length = -1)
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Basic
    @Column(name = "adressemail", nullable = false, length = -1)
    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UtilisateurEntity.class.getSimpleName() + "[", "]")
                .add("idutilisateur=" + idUtilisateur)
                .add("pseudo='" + pseudo + "'")
                .add("mdp='" + mdp + "'")
                .add("sel='" + sel + "'")
                .add("prenom='" + prenom + "'")
                .add("nom='" + nom + "'")
                .add("adresse='" + adresse + "'")
                .add("adresseMail='" + adresseMail + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtilisateurEntity that = (UtilisateurEntity) o;
        return idUtilisateur == that.idUtilisateur &&
                Objects.equals(pseudo, that.pseudo) &&
                Objects.equals(mdp, that.mdp) &&
                Objects.equals(sel, that.sel) &&
                Objects.equals(prenom, that.prenom) &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(adresse, that.adresse) &&
                Objects.equals(adresseMail, that.adresseMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtilisateur, pseudo, mdp, sel, prenom, nom, adresse, adresseMail);
    }
}
