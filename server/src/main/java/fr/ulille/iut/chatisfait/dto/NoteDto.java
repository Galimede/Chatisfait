package fr.ulille.iut.chatisfait.dto;

public class NoteDto {

    private int idNote;
    private int idArticle;
    private double note;
    private int nbNote;

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getId() {
        return idNote;
    }

    public void setId(int id) {
        this.idNote = id;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public int getNbNote() {
        return nbNote;
    }

    public void setNbNote(int nbNote) {
        this.nbNote = nbNote;
    }
}
