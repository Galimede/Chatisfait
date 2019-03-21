package fr.ulille.iut.chatisfait.ressources;

import fr.ulille.iut.chatisfait.dao.ArticleEntity;
import fr.ulille.iut.chatisfait.dao.DataAccess;
import fr.ulille.iut.chatisfait.dao.DatabaseConstraintException;
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

/**
 * ArticleRessource
 *
 * @author blochs
 */

@Path("/articles")

public class ArticleRessource {
    private final static Logger logger = LoggerFactory.getLogger(ArticleRessource.class);

    @Context
    public UriInfo uriInfo;

    public ArticleRessource(){}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArticleDto> getAll() {
        DataAccess dataAccess = DataAccess.begin();
        List<ArticleEntity> articleEntities = dataAccess.getAllArticles();
        dataAccess.closeConnection(true);
        return articleEntities.stream().map(ArticleEntity::articleToDto).collect(Collectors.toList());
    }

    @GET
    @Path("/{nom}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticle(@PathParam("nom") String nom) {
        DataAccess dataAccess = DataAccess.begin();
        ArticleEntity articleEntity = dataAccess.getArticleByNom(nom);
        if(articleEntity != null) {
            dataAccess.closeConnection(true);
            return Response.ok(articleEntity.articleToDto(articleEntity)).build();
        } else {
            dataAccess.closeConnection(false);
            return Response.status(Response.Status.NOT_FOUND).entity("article not found").build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ArticleDto articleDto) {
        DataAccess dataAccess = DataAccess.begin();
        ArticleEntity articleEntity = ArticleEntity.convertFromArticleDto(articleDto);
        if(articleDto.getNom() == null) {
            dataAccess.closeConnection(true);
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("name not specified").build();
        }
        try {
            int idArticle = dataAccess.createArticle(articleEntity);
            URI instanceUri = uriInfo.getAbsolutePathBuilder().path("" + idArticle).build();
            dataAccess.closeConnection(true);
            return Response.created(instanceUri).status(201).entity(articleEntity.articleToDto(articleEntity)).location(instanceUri).build();
        } catch (DatabaseConstraintException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @PUT
    @Path("/{nom}")
    public Response update(@PathParam("nom") String nom, ArticleEntity article) {
        DataAccess dataAccess = DataAccess.begin();
        ArticleEntity articleEntity = dataAccess.getArticleByNom(nom);
        if (articleEntity == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Article not found").build();
        } else {
            try {
                if(article.getNom() != null)
                articleEntity.setNom(article.getNom());
                if(article.getPrix() != 0)
                    articleEntity.setPrix(article.getPrix());
                if(article.getDescription() != null)
                    articleEntity.setDescription(article.getDescription());
                dataAccess.updateArticle(articleEntity);
                dataAccess.closeConnection(true);
                return Response.ok(articleEntity).build(); //  .created(instanceURI).build();
            } catch (Exception ex) {
                dataAccess.closeConnection(false);
                return Response.status(Response.Status.CONFLICT).entity("Duplicated name").build();
            }
        }
    }

    @DELETE
    @Path("/{nom}")
    public Response delete(@PathParam("nom") String nom){
        DataAccess dataAccess = DataAccess.begin();
        System.out.println(nom);
        try {
            dataAccess.deleteArticle(nom);
            dataAccess.closeConnection(true);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            dataAccess.closeConnection(false);
            return Response.status(Response.Status.NOT_FOUND).entity("Article not found").build();
        }

    }



}
