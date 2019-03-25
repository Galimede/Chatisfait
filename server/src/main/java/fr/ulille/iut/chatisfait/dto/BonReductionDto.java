package fr.ulille.iut.chatisfait.dto;

/**
 * BonReductionDto
 *
 * @author blochs
 */
public class BonReductionDto {
    private int idBonReduction;
    private int code;
    private int pourcentage;
    private String categorie;

    public int getIdBonReduction() {
        return idBonReduction;
    }

    public void setIdBonReduction(int idBonReduction) {
        this.idBonReduction = idBonReduction;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
