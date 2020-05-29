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
 * @author mmarvin
 */
@Path("reportes")
public class reportesServices {

    @EJB
    public SisAccesosFacadeLocal accesos;

    @GET
    @Path("reporteMay")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject consultaMay(@Context HttpServletRequest req, @Context SecurityContext securityContext,
            @QueryParam("inicio") int inicio, @QueryParam("xfin") int xfin, @QueryParam("anio") int anio) {
        JsonObject procesoObject = null;

        JsonObjectBuilder procesoBuilder = Json.createObjectBuilder();
        JsonArrayBuilder procesoArray = Json.createArrayBuilder();

        List<Object[]> reporteMay = accesos.reporteMay(inicio, xfin, anio);
        if (reporteMay.size() > 0) {
            for (Object[] val : reporteMay) {
                procesoBuilder.add("establecimiento", (String) val[0]).
                        add("maquina", (int) val[1]).
                        add("direccion", (String) val[2]).
                        add("venta", (BigDecimal) val[3]).
                        add("gananciaEstablecimiento", (BigDecimal) val[4]).
                        add("diferencia", (BigDecimal) val[5]);

                procesoArray.add(procesoBuilder);
            }
            procesoObject = Json.createObjectBuilder().add("reporteMay", procesoArray).build();

        } else {
            procesoObject = Json.createObjectBuilder().add("mensaje", "No existen Datos para el reporte").build();
        }

        return procesoObject;
    }

    @GET
    @Path("reporteMin")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject consultaMin(@Context HttpServletRequest req, @Context SecurityContext securityContext,
            @QueryParam("inicio") int inicio, @QueryParam("xfin") int xfin, @QueryParam("anio") int anio) {
        JsonObject procesoObject = null;

        JsonObjectBuilder procesoBuilder = Json.createObjectBuilder();
        JsonArrayBuilder procesoArray = Json.createArrayBuilder();

        List<Object[]> reporteMay = accesos.reporteMin(inicio, xfin, anio);
        if (reporteMay.size() > 0) {
            for (Object[] val : reporteMay) {
                procesoBuilder.add("establecimiento", (String) val[0]).
                        add("maquina", (int) val[1]).
                        add("direccion", (String) val[2]).
                        add("venta", (BigDecimal) val[3]).
                        add("gananciaEstablecimiento", (BigDecimal) val[4]).
                        add("diferencia", (BigDecimal) val[5]);

                procesoArray.add(procesoBuilder);
            }
            procesoObject = Json.createObjectBuilder().add("reporteMin", procesoArray).build();

        } else {
            procesoObject = Json.createObjectBuilder().add("mensaje", "No existen Datos para el reporte").build();
        }

        return procesoObject;
    }

    @GET
    @Path("reporteComercioMen")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject consultaComercioMen(@Context HttpServletRequest req, @Context SecurityContext securityContext,
            @QueryParam("mes") int mes, @QueryParam("tipo") String tipo, @QueryParam("anio") int anio) {
        JsonObject procesoObject = null;

        JsonObjectBuilder procesoBuilder = Json.createObjectBuilder();
        JsonArrayBuilder procesoArray = Json.createArrayBuilder();

        List<Object[]> reporteMay = accesos.reporteComercioMen(mes, tipo.trim(), anio);
        if (reporteMay.size() > 0) {
            for (Object[] val : reporteMay) {
                procesoBuilder.add("establecimiento", (String) val[0]).
                        add("direccion", (String) val[1]).
                        add("correo", (String) val[2]).
                        add("total", (BigDecimal) val[3]).
                        add("estadop", (String) val[4]);

                procesoArray.add(procesoBuilder);
            }
            procesoObject = Json.createObjectBuilder().add("reporteComercioMen", procesoArray).build();

        } else {
            procesoObject = Json.createObjectBuilder().add("mensaje", "No existen Datos para el reporte").build();
        }

        return procesoObject;
    }

    @GET
    @Path("reporteTiendasVent")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject consultaTiendasVent(@Context HttpServletRequest req, @Context SecurityContext securityContext) {
        JsonObject procesoObject = null;
        JsonObjectBuilder procesoBuilder = Json.createObjectBuilder();
        JsonArrayBuilder procesoArray = Json.createArrayBuilder();

        List<Object[]> reporteTien = accesos.reporteTienda(1);
        if (reporteTien.size() > 0) {
            for (Object[] val : reporteTien) {
                procesoBuilder.add("maquina", (int) val[0]).
                        add("establecimiento", (String) val[1]).
                        add("direccion", (String) val[2]).
                        add("prodVendidos", (long) val[3]).
                        add("venta", (BigDecimal) val[4]);

                procesoArray.add(procesoBuilder);
            }
            procesoObject = Json.createObjectBuilder().add("reportTiendaVent", procesoArray).build();

        } else {
            procesoObject = Json.createObjectBuilder().add("mensaje", "No existen Datos para el reporte").build();
        }

        return procesoObject;
    }

    @GET
    @Path("reporteMaquinaEst")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject consultaMaquinaEst(@Context HttpServletRequest req, @Context SecurityContext securityContext) {
        JsonObject procesoObject = null;
        JsonObjectBuilder procesoBuilder = Json.createObjectBuilder();
        JsonArrayBuilder procesoArray = Json.createArrayBuilder();

        List<Object[]> reporteMaq = accesos.reporteMaquinasEstado();
        if (reporteMaq.size() > 0) {
            for (Object[] val : reporteMaq) {
                procesoBuilder.add("estado", (String) val[0]).
                        add("motivo", (String) val[1]).
                        add("establecimiento", (String) val[2]).
                        add("ensamblador", (String) val[3]);

                procesoArray.add(procesoBuilder);
            }
            procesoObject = Json.createObjectBuilder().add("reporteTiendaEst", procesoArray).build();

        } else {
            procesoObject = Json.createObjectBuilder().add("mensaje", "No existen Datos para el reporte").build();
        }

        return procesoObject;
    }

    @GET
    @Path("reporteMaquinaEnsam")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject consultaMaquinaEnsam(@Context HttpServletRequest req, @Context SecurityContext securityContext) {
        JsonObject procesoObject = null;
        JsonObjectBuilder procesoBuilder = Json.createObjectBuilder();
        JsonArrayBuilder procesoArray = Json.createArrayBuilder();

        List<Object[]> reporteMaq = accesos.reporteMaquinasEnsambla();
        if (reporteMaq.size() > 0) {
            for (Object[] val : reporteMaq) {
                procesoBuilder.add("ensamblador", (String) val[1]).
                        add("numeroMaquinas", (long) val[2]);

                procesoArray.add(procesoBuilder);
            }
            procesoObject = Json.createObjectBuilder().add("reporteMaquinasEnsam", procesoArray).build();

        } else {
            procesoObject = Json.createObjectBuilder().add("mensaje", "No existen Datos para el reporte").build();
        }

        return procesoObject;
    }
    
    @GET
    @Path("reporteVentasRepartidor")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject consultaVentasRepartidor(@Context HttpServletRequest req, @Context SecurityContext securityContext,
            @QueryParam("mes") int mes,  @QueryParam("anio") int anio) {
        JsonObject procesoObject = null;

        JsonObjectBuilder procesoBuilder = Json.createObjectBuilder();
        JsonArrayBuilder procesoArray = Json.createArrayBuilder();

        List<Object[]> reporteMay = accesos.reporteVentasRepartidor(mes, anio);
        if (reporteMay.size() > 0) {
            for (Object[] val : reporteMay) {
                procesoBuilder.add("repartidor", (String) val[0]).
                        add("ruta", (String) val[2]).
                        add("montoVenta", (BigDecimal) val[3]).
                        add("banco", (String) val[4]).
                        add("deposito", (BigDecimal) val[5]);

                procesoArray.add(procesoBuilder);
            }
            procesoObject = Json.createObjectBuilder().add("reporteVentRepar", procesoArray).build();

        } else {
            procesoObject = Json.createObjectBuilder().add("mensaje", "No existen Datos para el reporte").build();
        }

        return procesoObject;
    }
    
     @GET
    @Path("reporteProDevMaq")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject consultaProdDevMaq(@Context HttpServletRequest req, @Context SecurityContext securityContext) {
        JsonObject procesoObject = null;
        JsonObjectBuilder procesoBuilder = Json.createObjectBuilder();
        JsonArrayBuilder procesoArray = Json.createArrayBuilder();

        List<Object[]> reporteMaq = accesos.reporteProdDevMaq();
        if (reporteMaq.size() > 0) {
            for (Object[] val : reporteMaq) {
                procesoBuilder.add("motEstMaq", (String) val[0]).
                        add("promedio", (BigDecimal) val[1]);

                procesoArray.add(procesoBuilder);
            }
            procesoObject = Json.createObjectBuilder().add("reporteProDevMaq", procesoArray).build();

        } else {
            procesoObject = Json.createObjectBuilder().add("mensaje", "No existen Datos para el reporte").build();
        }

        return procesoObject;
    }

}
