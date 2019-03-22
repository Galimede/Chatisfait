package fr.ulille.iut.chatisfait.dao;

import fr.ulille.iut.chatisfait.dto.ArticleDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;


/**
 * ArticleEntity
 *
 * @author blochs
 */

@Entity
@Table(name = "article")

@NamedQueries({
        @NamedQuery(name = "FindAllArticles",query = "SELECT a from ArticleEntity a"),
        @NamedQuery(name = "FindArticleByNom",query = "SELECT a from ArticleEntity a where a.nom = :anom")
})


public class ArticleEntity {

    private int idArticle;
    private String nom;
    private double prix;
    private String description;
    private String categorie;
    private String image;

    private final static Logger logger = LoggerFactory.getLogger(ArticleEntity.class);
    private static ModelMapper modelMapper = new ModelMapper();

    public static ArticleDto articleToDto(ArticleEntity articleEntity) {
        return modelMapper.map(articleEntity,ArticleDto.class);
    }

    public static  ArticleEntity convertFromArticleDto(ArticleDto articleDto) {
        return modelMapper.map(articleDto, ArticleEntity.class);
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idarticle", nullable = false)
    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idarticle) {
        this.idArticle = idarticle;
    }

    @Basic
    @Column(name = "nom", nullable = false, length = 20)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "prix", nullable = false)
    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 250)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "categorie", length = 20)
    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Basic
    @Column(name = "image", length = 100)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
