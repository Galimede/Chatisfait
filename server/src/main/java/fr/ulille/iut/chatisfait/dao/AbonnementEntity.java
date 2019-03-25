package fr.ulille.iut.chatisfait.dao;


import fr.ulille.iut.chatisfait.dto.AbonnementDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@Table(name = "abonnement")

@NamedQueries({
        @NamedQuery(name = "FindAllAbonnements",query = "SELECT a from AbonnementEntity a"),
        @NamedQuery(name = "FindAbonnementById",query = "SELECT a from AbonnementEntity a where a.idAbonnement = :aidabonnement")
})

public class AbonnementEntity {

    private int idAbonnement;
    private int idUtilisateur;
    private int idArticle;
    private int age;
    private String poil;
    private boolean sterilise;
    private double poids;
    private String nom;
    private int choix;

    private final static Logger logger = LoggerFactory.getLogger(AbonnementEntity.class);
    private static ModelMapper modelMapper = new ModelMapper();

    public static AbonnementDto abonneToDto(AbonnementEntity abonneEntity) {
        return modelMapper.map(abonneEntity,AbonnementDto.class);
    }

    public static  AbonnementEntity convertFromAbonneDto(AbonnementDto abonneDto) {
        return modelMapper.map(abonneDto, AbonnementEntity.class);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idabonnement", nullable = false)
    public int getIdAbonnement() {
        return idAbonnement;
    }

    public void setIdAbonnement(int idAbonnement) {
        this.idAbonnement = idAbonnement;
    }

    @Basic
    @Column(name = "idutilisateur", nullable = false)
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idutilisateur) {
        this.idUtilisateur = idutilisateur;
    }

    @Basic
    @Column(name = "idarticle", nullable = false)
    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    @Basic
    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Basic
    @Column(name = "poil", length = 20)
    public String getPoil() {
        return poil;
    }

    public void setPoil(String poil) {
        this.poil = poil;
    }

    @Basic
    @Column(name="sterilise")
    public boolean isSterilise() {
        return sterilise;
    }

    public void setSterilise(boolean sterilise) {
        this.sterilise = sterilise;
    }

    @Basic
    @Column(name="poids")
    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    @Basic
    @Column(name="nom", length = 20)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name="choix")
    public int getChoix() {
        return choix;
    }

    public void setChoix(int choix) {
        this.choix = choix;
    }
}

