package fr.ulille.iut.chatisfait.ressources;

import fr.ulille.iut.chatisfait.dao.DataAccess;
import fr.ulille.iut.chatisfait.dao.DatabaseConstraintException;
import fr.ulille.iut.chatisfait.dao.UtilisateurEntity;
import fr.ulille.iut.chatisfait.dto.UtilisateurDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UtilisateurRessource
 *
 * @author blochs
 */
@Path("/utilisateurs")
public class UtilisateurRessource {

    private final static Logger logger = LoggerFactory.getLogger(UtilisateurRessource.class);

    @Context
    public UriInfo uriInfo;

    public UtilisateurRessource(){}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UtilisateurDto> getAll() {
        DataAccess dataAccess = DataAccess.begin();
        List<UtilisateurEntity> utilisateurEntities = dataAccess.getAllUtilisateurs();
        dataAccess.closeConnection(true);
        return utilisateurEntities.stream().map(UtilisateurEntity::utilisateurToDto).collect(Collectors.toList());
    }

    @GET
    @Path("{pseudo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUtilisateur(@PathParam("pseudo") String pseudo) {
        DataAccess dataAccess = DataAccess.begin();
        UtilisateurEntity utilisateurEntity = dataAccess.getUtilisateurByPseudo(pseudo);
        if ( utilisateurEntity != null ) {
            dataAccess.closeConnection(true);
            return Response.ok(utilisateurEntity.utilisateurToDto(utilisateurEntity)).build();
        }else {
            dataAccess.closeConnection(false);
            return Response.status(Response.Status.NOT_FOUND).entity("Utilisateur not found").build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(UtilisateurDto utilisateurDto) {
        DataAccess dataAccess = DataAccess.begin();
        UtilisateurEntity utilisateurEntity = UtilisateurEntity.convertFromUtilisateurDto(utilisateurDto);
        if(utilisateurDto.getPseudo() == null ) {
            dataAccess.closeConnection(false);
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("name not specified").build();
        }

        try {
            int id = dataAccess.createUtilisateur(utilisateurEntity);
            URI instanceURI = uriInfo.getAbsolutePathBuilder().path("" + id).build();
            dataAccess.closeConnection(true);
            return Response.created(instanceURI).status(201).entity(UtilisateurEntity.utilisateurToDto(utilisateurEntity)).location(instanceURI).build();
        } catch (DatabaseConstraintException e) {
            e.printStackTrace();
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @PUT
    @Path("/{pseudo}")
    public Response update(@PathParam("pseudo") String pseudo, UtilisateurEntity utilisateur) {
        DataAccess dataAccess = DataAccess.begin();
        UtilisateurEntity utilisateurEntity = dataAccess.getUtilisateurByPseudo(pseudo);
        if (utilisateurEntity == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Utilisateur not found").build();
        } else {
            try {
                utilisateurEntity.setAbonne(utilisateur.isAbonne());
                utilisateurEntity.setAdmin(utilisateur.isAdmin());
                if(utilisateur.getSel() != null)
                    utilisateurEntity.setSel(utilisateur.getSel());
                if(utilisateur.getAdresse() != null)
                    utilisateurEntity.setAdresse(utilisateur.getAdresse());
                if(utilisateur.getAdresseMail() != null)
                    utilisateurEntity.setAdresseMail(utilisateur.getAdresseMail());
                if(utilisateur.getMdp() != null)
                    utilisateurEntity.setMdp(utilisateur.getMdp());
                if (utilisateur.getPrenom() != null)
                    utilisateurEntity.setPrenom(utilisateur.getPrenom());
                if(utilisateur.getPseudo() != null)
                    utilisateurEntity.setPseudo(utilisateur.getPseudo());
                if(utilisateurEntity.getNom() != null)
                    utilisateurEntity.setNom(utilisateur.getNom());
                dataAccess.updateUtilisateur(utilisateurEntity);
                dataAccess.closeConnection(true);
                return Response.ok(utilisateurEntity).build(); //  .created(instanceURI).build();
            } catch (Exception ex) {
                dataAccess.closeConnection(false);
                return Response.status(Response.Status.CONFLICT).entity("Duplicated name").build();
            }
        }
    }

    @DELETE
    @Path("/{idutilisateur}")
    public Response delete(@PathParam("idutilisateur") int idUtilisateur){
        DataAccess dataAccess = DataAccess.begin();
        try {
            dataAccess.deleteUtilisateur(idUtilisateur);
            dataAccess.closeConnection(true);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            dataAccess.closeConnection(false);
            return Response.status(Response.Status.NOT_FOUND).entity("Utilisateur not found").build();
        }


    }

}
