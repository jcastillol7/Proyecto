/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umg.edu.jersey.resource;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import umg.edu.jersey.session.SisAccesosFacadeLocal;

/**
 *
 * @author mmavin
 */
@Path("loginGolosinas")
public class LoginService {

    @EJB
    public SisAccesosFacadeLocal accesos;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject consultaMq(@Context HttpServletRequest req, @Context SecurityContext securityContext,
            @QueryParam("usuario") String usuario, @QueryParam("clave") String clave) {
        JsonObject procesoObject = null;

        JsonObjectBuilder procesoBuilder = Json.createObjectBuilder();
        JsonArrayBuilder procesoArray = Json.createArrayBuilder();

        List<Object[]> buscaLogin = accesos.buscaLogin(usuario.trim(), clave.trim());
        if (buscaLogin.size() > 0) {
            for (Object[] val : buscaLogin) {
                procesoBuilder.
                        add("usuario", (String) val[0]).
                        add("nombre", (String) val[1]).
                        add("apellido", (String) val[2]).
                        add("correo", (String) val[3]);

                procesoArray.add(procesoBuilder);
            }
            procesoObject = Json.createObjectBuilder().add("Login", procesoArray).build();

        } else {
            procesoObject = Json.createObjectBuilder().add("mensaje", "Usuario no existe").build();
        }

        return procesoObject;
    }

}
