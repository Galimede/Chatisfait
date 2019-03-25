package fr.ulille.iut.chatisfait.dto;

public class NoteDto {

    private int idNote;
    private int idArticle;
    private double noteTotale;
    private int nbNote;

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public double getNoteTotale() {
        return noteTotale;
    }

    public void setNoteTotale(double noteTotale) {
        this.noteTotale = noteTotale;
    }

    public int getNbNote() {
        return nbNote;
    }

    public void setNbNote(int nbNote) {
        this.nbNote = nbNote;
    }
}
