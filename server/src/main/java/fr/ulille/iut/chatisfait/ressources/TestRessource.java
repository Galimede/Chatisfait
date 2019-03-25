package fr.ulille.iut.chatisfait.ressources;
;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;

/**
 * TestRessource
 *
 * @author blochs
 */


@Path("/test")

public class TestRessource {

    @Context
    public UriInfo uriInfo;

    public TestRessource() {}

    @GET
    @Produces("image/png")
    public Response envoyeImage() {
        File file = new File("/home/infoetu/blochs/IdeaProjects/squelette-projet-s4-git/server/src/main/resources/static/images/articles/brosse.jpg");
        return Response.ok(file).build();
    }
}
