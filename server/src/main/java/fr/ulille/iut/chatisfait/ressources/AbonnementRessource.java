package fr.ulille.iut.chatisfait.ressources;

import fr.ulille.iut.chatisfait.dao.*;
import fr.ulille.iut.chatisfait.dto.AbonnementDto;
import fr.ulille.iut.chatisfait.dto.ArticleDto;
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

@Path("/abonnements")
public class AbonnementRessource {
    private final static Logger logger = LoggerFactory.getLogger(ArticleRessource.class);

    @Context
    public UriInfo uriInfo;

    public AbonnementRessource(){}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AbonnementDto> getAll() {
        DataAccess dataAccess = DataAccess.begin();
        List<AbonnementEntity> abonnementEntityList = dataAccess.getAllAbonnements();
        dataAccess.closeConnection(true);
        return abonnementEntityList.stream().map(AbonnementEntity::abonneToDto).collect(Collectors.toList());
    }

    @GET
    @Path("/{idabonnement}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticle(@PathParam("idabonnement") int id) {
        DataAccess dataAccess = DataAccess.begin();
        AbonnementEntity abonnementEntity = dataAccess.getAbonnementById(id);
        if(abonnementEntity != null) {
            dataAccess.closeConnection(true);
            return Response.ok(abonnementEntity.abonneToDto(abonnementEntity)).build();
        } else {
            dataAccess.closeConnection(false);
            return Response.status(Response.Status.NOT_FOUND).entity("abnnement not found").build();
        }
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(AbonnementDto abonnementDto) {
        DataAccess dataAccess = DataAccess.begin();
        AbonnementEntity abonnementEntity = AbonnementEntity.convertFromAbonneDto(abonnementDto);
        if(abonnementEntity.getIdAbonnement() == 0) {
            dataAccess.closeConnection(true);
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("id not specified").build();
        }
        try {
            int idAbonnement = dataAccess.createAbonnement(abonnementEntity);
            URI instanceUri = uriInfo.getAbsolutePathBuilder().path("" + idAbonnement).build();
            dataAccess.closeConnection(true);
            return Response.created(instanceUri).status(201).entity(abonnementEntity.abonneToDto(abonnementEntity)).location(instanceUri).build();
        } catch (DatabaseConstraintException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @PUT
    @Path("/{idabonnement}")
    public Response update(@PathParam("idabonnement") int idAbonnement, AbonnementEntity abonne) {
        DataAccess dataAccess = DataAccess.begin();
        AbonnementEntity abonnementEntity = dataAccess.getAbonnementById(idAbonnement);
        if (abonnementEntity == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Abonne not found").build();
        } else {
            System.out.println("lol");
            try {
                if(abonne.get != null)
                    commandeEntity.setAdresse(commande.getAdresse());
                if (commande.getAdresseMail() != null)
                    commandeEntity.setAdresseMail(commande.getAdresseMail());
                if(commande.getDateCommande() != null)
                    commandeEntity.setDateCommande(commande.getDateCommande());
                if(commande.getPrix() != 0)
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


}