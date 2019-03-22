package fr.ulille.iut.chatisfait.dao;


import fr.ulille.iut.chatisfait.dto.AbonnementDto;
import fr.ulille.iut.chatisfait.dto.ArticleDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "article")

@NamedQueries({
        @NamedQuery(name = "FindAllAbonnements",query = "SELECT a from AbonnementEntity a"),
        @NamedQuery(name = "FindAbonnementById",query = "SELECT a from AbonnementEntity a where a.i = :aid")
})

public class AbonnementEntity {

    private int idAbonnement;
    private int idutilisateur;
    private int idArticle;
    private String typeAbonne;

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
    public int getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
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
    @Column(name = "typeabonne", length = 60)
    public String getTypeAbonne() {
        return typeAbonne;
    }

    public void setTypeAbonne(String typeAbonne) {
        this.typeAbonne = typeAbonne;
    }
}
