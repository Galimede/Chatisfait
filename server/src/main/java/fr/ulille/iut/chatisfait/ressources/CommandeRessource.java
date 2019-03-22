package fr.ulille.iut.chatisfait.ressources;

import fr.ulille.iut.chatisfait.dao.CommandeEntity;
import fr.ulille.iut.chatisfait.dao.DataAccess;
import fr.ulille.iut.chatisfait.dao.DatabaseConstraintException;
import fr.ulille.iut.chatisfait.dto.CommandeDto;
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
 * CommandeRessource
 *
 * @author blochs
 */


@Path("/commandes")
public class CommandeRessource {

    private final static Logger logger = LoggerFactory.getLogger(CommandeRessource.class);

    @Context
    public UriInfo uriInfo;

    public CommandeRessource(){}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CommandeDto> getAll() {
        DataAccess dataAccess = DataAccess.begin();
        List<CommandeEntity> commandeEntities = dataAccess.getAllCommandes();
        dataAccess.closeConnection(true);
        return commandeEntities.stream().map(CommandeEntity::commandeToDto).collect(Collectors.toList());
    }

    @GET
    @Path("{idcommande}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommande(@PathParam("idcommande")  int idCommande) {
        DataAccess dataAccess = DataAccess.begin();
        CommandeEntity commandeEntity = dataAccess.getCommandeById(idCommande);
        if ( commandeEntity != null ) {
            dataAccess.closeConnection(true);
            return Response.ok(commandeEntity.commandeToDto(commandeEntity)).build();
        }else {
            dataAccess.closeConnection(false);
            return Response.status(Response.Status.NOT_FOUND).entity("Commande not found").build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CommandeDto commandeDto) {
        DataAccess dataAccess = DataAccess.begin();
        CommandeEntity commandeEntity = CommandeEntity.convertFromCommandeDto(commandeDto);
        if(commandeDto.getIdUtilisateur() == 0 ) {
            dataAccess.closeConnection(false);
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("name not specified").build();
        }

        try {
            int id = dataAccess.createCommande(commandeEntity);
            URI instanceURI = uriInfo.getAbsolutePathBuilder().path("" + id).build();
            dataAccess.closeConnection(true);
            return Response.created(instanceURI).status(201).entity(CommandeEntity.commandeToDto(commandeEntity)).location(instanceURI).build();
        } catch (DatabaseConstraintException e) {
            e.printStackTrace();
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @PUT
    @Path("/{idcommande}")
    public Response update(@PathParam("idcommande") int idCommande, CommandeEntity commande) {
        DataAccess dataAccess = DataAccess.begin();
        CommandeEntity commandeEntity = dataAccess.getCommandeById(idCommande);
        if (commandeEntity == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Commande not found").build();
        } else {
            try {
                if(commande.getAdresse() != null)
                    commandeEntity.setAdresse(commande.getAdresse());
                if (commande.getAdresseMail() != null)
                    commandeEntity.setAdresseMail(commande.getAdresseMail());
                if(commande.getDateCommande() != null)
                    commandeEntity.setDateCommande(commande.getDateCommande());
                if(commande.getPrix() == 0)
                    commandeEntity.setPrix(commande.getPrix());
                if(commande.getPrenom() != null)
                    commandeEntity.setPrenom(commande.getPrenom());
                if(commande.getNom() != null)
                    commandeEntity.setNom(commande.getNom());
                dataAccess.updateCommande(commandeEntity);
                dataAccess.closeConnection(true);
                return Response.ok(commandeEntity).build(); //  .created(instanceURI).build();
            } catch (Exception ex) {
                dataAccess.closeConnection(false);
                return Response.status(Response.Status.CONFLICT).entity("Commande probleme").build();
            }
        }
    }

    @DELETE
    @Path("/{idcommande}")
    public Response delete(@PathParam("idcommande") int idCommande){
        DataAccess dataAccess = DataAccess.begin();
        try {
            dataAccess.deleteCommande(idCommande);
            dataAccess.closeConnection(true);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            dataAccess.closeConnection(false);
            return Response.status(Response.Status.NOT_FOUND).entity("Commande not found").build();
        }

    }


}
