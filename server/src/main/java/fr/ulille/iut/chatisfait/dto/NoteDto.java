package fr.ulille.iut.chatisfait.dto;

public class NoteDto {

    private int idNote;
    private int idArticle;
    private double note;
    private int nbnote;

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

    public int getNbnote() {
        return nbnote;
    }

    public void setNbnote(int nbnote) {
        this.nbnote = nbnote;
    }
}
