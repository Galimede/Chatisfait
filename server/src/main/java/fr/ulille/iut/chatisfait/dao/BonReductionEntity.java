package fr.ulille.iut.chatisfait.dao;

import fr.ulille.iut.chatisfait.dto.BonReductionDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

/**
 * BonReductionEntity
 *
 * @author blochs
 */

@Entity
@Table(name = "bonreduction")

@NamedQueries({
        @NamedQuery(name = "FindAllBonReductions",query = "SELECT b from BonReductionEntity b"),
        @NamedQuery(name = "FindBonReductionByCode",query = "SELECT b from BonReductionEntity b where b = :bnom")
})

public class BonReductionEntity {
    private int idBonReduction;
    private int code;
    private int pourcentage;
    private String categorie;

    private final static Logger logger = LoggerFactory.getLogger(ArticleEntity.class);
    private static ModelMapper modelMapper = new ModelMapper();

    public static BonReductionDto bonReductionToDto(BonReductionEntity bonReductionEntity) {
        return modelMapper.map(bonReductionEntity, BonReductionDto.class);
    }

    public static  BonReductionEntity convertFromBonReductionDto(BonReductionDto bonReductionDto) {
        return modelMapper.map(bonReductionDto, BonReductionEntity.class);
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbonreduction", nullable = false)
    public int getIdBonReduction() {
        return idBonReduction;
    }

    public void setIdBonReduction(int idBonReduction) {
        this.idBonReduction = idBonReduction;
    }

    @Basic
    @Column(name = "code")
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Basic
    @Column(name = "pourcentage")
    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    @Basic
    @Column(name = "categorie", length = 20)
    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
