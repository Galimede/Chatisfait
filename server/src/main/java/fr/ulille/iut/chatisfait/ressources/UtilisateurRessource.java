package fr.ulille.iut.chatisfait.ressources;

import fr.ulille.iut.chatisfait.dao.DataAccess;
import fr.ulille.iut.chatisfait.dao.DatabaseConstraintException;
import fr.ulille.iut.chatisfait.dao.PizzaEntity;
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
        if(utilisateurDto.getPseudo() == null) {
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

    @GET
    @Path("{idutilisateur}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByIdUtilisateur(@PathParam("idutilisateur") int idUtilisateur) {
        DataAccess dataAccess = DataAccess.begin();
        UtilisateurEntity u = dataAccess.getUtilisateurById(idUtilisateur);
        if ( u != null ) {
            dataAccess.closeConnection(true);
            return Response.ok(UtilisateurEntity.utilisateurToDto(u)).build();
        } else {
            dataAccess.closeConnection(false);
            return Response.status(Response.Status.NOT_FOUND).entity("Users not found").build();
        }
    }

}
