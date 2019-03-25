package fr.ulille.iut.chatisfait.ressources;

import fr.ulille.iut.chatisfait.dao.DataAccess;
import fr.ulille.iut.chatisfait.dao.DatabaseConstraintException;
import fr.ulille.iut.chatisfait.dao.NoteEntity;
import fr.ulille.iut.chatisfait.dto.NoteDto;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * NoteRessource
 *
 * @author blochs
 */

@Path("/notes")
public class NoteRessource {

    @Context
    public UriInfo uriInfo;

    public NoteRessource(){}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<NoteDto> getAll() {
        DataAccess dataAccess = DataAccess.begin();
        List<NoteEntity> noteEntities = dataAccess.getAllNotes();
        dataAccess.closeConnection(true);
        return noteEntities.stream().map(NoteEntity::noteToDto).collect(Collectors.toList());
    }

    @GET
    @Path("/{idarticle}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticle(@PathParam("idarticle") int idarticle) {
        DataAccess dataAccess = DataAccess.begin();
        NoteEntity noteEntity = dataAccess.getNoteByIdArticle(idarticle);
        if(noteEntity != null) {
            dataAccess.closeConnection(true);
            return Response.ok(noteEntity.noteToDto(noteEntity)).build();
        } else {
            dataAccess.closeConnection(false);
            return Response.status(Response.Status.NOT_FOUND).entity("note not found").build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(NoteDto noteDto) {
        DataAccess dataAccess = DataAccess.begin();
        NoteEntity noteEntity = NoteEntity.convertFromNoteDto(noteDto);
        if(noteEntity.getIdNote() < 0) {
            dataAccess.closeConnection(true);
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("id not specified").build();
        }
        try {
            int idNote = dataAccess.createNote(noteEntity);
            URI instanceUri = uriInfo.getAbsolutePathBuilder().path("" + idNote).build();
            dataAccess.closeConnection(true);
            return Response.created(instanceUri).status(201).entity(noteEntity.noteToDto(noteEntity)).location(instanceUri).build();
        } catch (DatabaseConstraintException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @PUT
    @Path("/{idarticle}")
    public Response update(@PathParam("idarticle") int idArticle, NoteEntity note) {
        DataAccess dataAccess = DataAccess.begin();
        NoteEntity noteEntity = dataAccess.getNoteByIdArticle(idArticle);
        if (noteEntity == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Note not found").build();
        } else {
            try {
                if (note.getIdArticle() != 0)
                    noteEntity.setIdArticle(note.getIdArticle());
                if (note.getNbNote() != 0)
                    noteEntity.setNbNote(note.getNbNote());
                if (note.getNoteTotale() != 0)
                    noteEntity.setNoteTotale(note.getNoteTotale());
                dataAccess.updateNote(noteEntity);
                dataAccess.closeConnection(true);
                return Response.ok(noteEntity).build(); //  .created(instanceURI).build();
            } catch (Exception ex) {
                dataAccess.closeConnection(false);
                return Response.status(Response.Status.CONFLICT).entity("Note probleme").build();
            }
        }
    }

    @DELETE
    @Path("/{idarticle}")
    public Response delete(@PathParam("idarticle") int idArticle){
        DataAccess dataAccess = DataAccess.begin();
        try {
            dataAccess.deleteNote(idArticle);
            dataAccess.closeConnection(true);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            dataAccess.closeConnection(false);
            return Response.status(Response.Status.NOT_FOUND).entity("Note not found").build();
        }

    }


}
