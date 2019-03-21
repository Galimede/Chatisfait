package fr.ulille.iut.chatisfait.dao;

import fr.ulille.iut.chatisfait.dto.CommandeDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.sql.Date;

/**
 * CommandeEntity
 *
 * @author blochs
 */


@Table(name = "commande")
@NamedQueries({
        @NamedQuery(name = "FindAllCommandes",query = "SELECT c from CommandeEntity c"),
        @NamedQuery(name = "FindCommandById",query = "SELECT c from CommandeEntity c where c.idcommande = :cidcommande")
})


public class CommandeEntity {
    private int idCommande;
    private int idUtilisateur;
    private double prix;
    private Date date;
    private String adresseMail;
    private String adresse;
    private String nom;
    private String prenom;
    private final static Logger logger = LoggerFactory.getLogger(CommandeEntity.class);
    private static ModelMapper modelMapper = new ModelMapper();


    public static CommandeDto commandeToDto(CommandeEntity commandeEntity) {
        return modelMapper.map(commandeEntity,CommandeDto.class);
    }

    public static  CommandeEntity convertFromCommandeDto(CommandeDto commandeDto) {
        return modelMapper.map(commandeDto, CommandeEntity.class);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcommande", nullable = false)
    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    @Basic
    @Column(name = "idutilisateur" , nullable = false)
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Basic
    @Column(name = "prix")
    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "adressemail", length = 70)
    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    @Basic
    @Column(name = "adresse", length = 60)
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Basic
    @Column(name = "tutu", length = 20)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}