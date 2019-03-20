package fr.ulille.iut.chatisfait;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/v1/")
public class ApiV1 extends ResourceConfig {

    public ApiV1() {
        packages("fr.ulille.iut.chatisfait.ressources");
        register(CORSFilter.class);

    }

}

