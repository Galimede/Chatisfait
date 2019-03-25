package fr.ulille.iut.chatisfait.ressources;

import fr.ulille.iut.chatisfait.dao.*;
import fr.ulille.iut.chatisfait.dto.AbonnementDto;
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
            return Response.status(Response.Status.NOT_FOUND).entity("abonnement not found").build();
        }
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(AbonnementDto abonnementDto) {
        DataAccess dataAccess = DataAccess.begin();
        AbonnementEntity abonnementEntity = AbonnementEntity.convertFromAbonneDto(abonnementDto);
        if(abonnementDto.getIdUtilisateur() == 0 || abonnementDto.getIdArticle() == 0) {
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
            try {
                if (abonne.getIdArticle() != 0)
                    abonnementEntity.setIdAbonnement(abonne.getIdArticle());
                if (abonne.getIdUtilisateur() != 0)
                    abonnementEntity.setIdAbonnement(abonne.getIdUtilisateur());
                if (abonne.getNom() != null)
                    abonnementEntity.setNom(abonne.getNom());
                if (abonne.getAge() != 0)
                    abonnementEntity.setAge(abonne.getAge());
                if (abonne.getPoids() != 0)
                    abonnementEntity.setPoids(abonne.getPoids());
                if (abonne.getPoil() != null)
                    abonnementEntity.setPoil(abonne.getPoil());
                if (abonne.getChoix() != 0)
                    abonnementEntity.setChoix(abonne.getChoix());
                dataAccess.updateAbonnement(abonnementEntity);
                dataAccess.closeConnection(true);
                return Response.ok(abonnementEntity).build(); //  .created(instanceURI).build();
            } catch (Exception ex) {
                dataAccess.closeConnection(false);
                return Response.status(Response.Status.CONFLICT).entity("Abonnement probleme").build();
            }
        }
    }

    @DELETE
    @Path("/{idabonnement}")
    public Response delete(@PathParam("idabonnement") int idAbonnement){
        DataAccess dataAccess = DataAccess.begin();
        try {
            dataAccess.deleteAbonnement(idAbonnement);
            dataAccess.closeConnection(true);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            dataAccess.closeConnection(false);
            return Response.status(Response.Status.NOT_FOUND).entity("Abonnement not found").build();
        }

    }


}
