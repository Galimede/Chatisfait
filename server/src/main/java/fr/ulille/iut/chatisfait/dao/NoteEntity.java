package fr.ulille.iut.chatisfait.dao;

import fr.ulille.iut.chatisfait.dto.NoteDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

/**
 * NoteEntity
 *
 * @author blochs
 */

@Entity
@Table(name = "note")

@NamedQueries({
        @NamedQuery(name = "FindAllNotes",query = "SELECT n from NoteEntity n"),
        @NamedQuery(name = "FindNoteByIdArticle",query = "SELECT n from NoteEntity n where n.idarticle = :nidarticle")
})

public class NoteEntity {

    private int idNote;
    private int idArticle;
    private double note;
    private int nbNote;

    private final static Logger logger = LoggerFactory.getLogger(ArticleEntity.class);
    private static ModelMapper modelMapper = new ModelMapper();

    public static NoteDto noteToDto(NoteEntity noteEntity) {
        return modelMapper.map(noteEntity,NoteDto.class);
    }

    public static  NoteEntity convertFromNoteDto(NoteDto noteDto) {
        return modelMapper.map(noteDto, NoteEntity.class);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnote", nullable = false)
    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
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
    @Column(name = "note")
    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    @Basic
    @Column(name = "nbnote")
    public int getNbNote() {
        return nbNote;
    }

    public void setNbNote(int nbNote) {
        this.nbNote = nbNote;
    }
}
