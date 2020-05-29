/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umg.edu.jersey.resource;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import umg.edu.jersey.clases.Padl;
import umg.edu.jersey.session.SisAccesosFacadeLocal;

/**
 *
 * @author mmarvin
 */
@Path("accesos")
public class AccesosService {

    @EJB
    public SisAccesosFacadeLocal accesos;

    public AccesosService() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject consultaMq(@Context HttpServletRequest req, @Context SecurityContext securityContext,
            @QueryParam("usuario") String usuario) {
        JsonObject procesoObject = null;
        if (!usuario.equals(" ") && usuario != null) {
            JsonObjectBuilder procesoBuilder = Json.createObjectBuilder();
            JsonArrayBuilder procesoArray = Json.createArrayBuilder();

            List<Object[]> consultaAccesos = accesos.consultaAccesos(usuario);
            if (consultaAccesos.size() > 0) {
                for (Object[] val : consultaAccesos) {
                    procesoBuilder.add("idUsuario", (int) val[0]).
                            add("usuario", (String) val[1]).
                            add("nombre", (String) val[2]).
                            add("apellido", (String) val[3]).
                            add("clave", (String) val[4]).
                            add("idModulo", (int) val[5]).
                            add("modulo", (String) val[6]).
                            add("idPrograma", (int) val[7]).
                            add("nombrePrograma", (String) val[8]).
                            add("titulo", (String) val[9]).
                            add("tipo", (String) val[10]);

                    procesoArray.add(procesoBuilder);
                }
                procesoObject = Json.createObjectBuilder().add("accesos", procesoArray).build();

            } else {
                procesoObject = Json.createObjectBuilder().add("mensaje", "No existen accesos para este usuario").build();
            }
        } else {
            procesoObject = Json.createObjectBuilder().add("mensaje", "Ingrese todos los parametros").build();
        }
        return procesoObject;

    }

}
