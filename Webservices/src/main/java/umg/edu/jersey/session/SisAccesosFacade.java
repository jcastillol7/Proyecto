/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umg.edu.jersey.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import umg.edu.jersey.entity.SisAccesos;

/**
 *
 * @author mmavin
 */
@Stateless
public class SisAccesosFacade extends AbstractFacade<SisAccesos> implements SisAccesosFacadeLocal {

    @PersistenceContext(unitName = "persistencia")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SisAccesosFacade() {
        super(SisAccesos.class);
    }

    @Override
    public List<Object[]> consultaAccesos(String usuario) {
        Query query = em.createNativeQuery("select b.id as idUsuario,b.usuario,b.nombre,b.apellido,b.clave,c.id as idModulo,c.nombre as modulo,\n"
                + "d.id as idPrograma,d.nombre as nomPrograma,d.titulo,d.tipo\n"
                + " from sis_accesos a \n"
                + "left join sis_usuarios b on a.id_usuario=b.id\n"
                + "left join sis_modulos c on a.id_modulo = c.id\n"
                + "left join sis_programas d on a.id_programa = d.id\n"
                + "where b.usuario= ? ");
        query.setParameter(1, usuario.trim().toUpperCase());
        return query.getResultList();
    }

    public List<Object[]> buscaLogin(String usuario, String clave) {
        Query query = em.createNativeQuery("select a.usuario,a.nombre,a.apellido,a.correo from sis_usuarios a where a.usuario=? and a.clave=? ");
        query.setParameter(1, usuario.trim());
        query.setParameter(2, clave.trim());
        return query.getResultList();

    }

    @Override
    public List<Object[]> reporteMay(int ini, int fin, int anio) {
        Query query = em.createNativeQuery("call reporteMay (?,?,?);");
        query.setParameter(1, ini);
        query.setParameter(2, fin);
        query.setParameter(3, anio);
        return query.getResultList();
    }

    @Override
    public List<Object[]> reporteMin(int ini, int fin, int anio) {
        Query query = em.createNativeQuery("call reporteMin(?,?,?);");
        query.setParameter(1, ini);
        query.setParameter(2, fin);
        query.setParameter(3, anio);
        return query.getResultList();
    }

    @Override
    public List<Object[]> reporteComercioMen(int mes, String tipo, int anio) {
        Query query = em.createNativeQuery("call reporteMensualFichaComercio(?,?,?);");
        query.setParameter(1, mes);
        query.setParameter(2, tipo);
        query.setParameter(3, anio);
        return query.getResultList();
    }

    @Override
    public List<Object[]> reporteTienda(int id) {
        Query query = em.createNativeQuery(" SELECT maq.idMaquina AS maquina,\n"
                + "      est.nombre_estbnto AS establecimiento,\n"
                + "      concat(uest.calle_ue, ' calle', uest.avenida_ue, ' avenida', ' ', uest.casa_ue, ' zona ', uest.zona_ue, ' ', uest.municipio_ue, ' ', uest.departamento_ue) AS direccion,\n"
                + "      count(ven.monto_vta) AS prodVendidos,\n"
                + "      sum(ven.monto_vta) AS venta\n"
                + "FROM ubicacion_establecimiento uest\n"
                + "INNER JOIN establecimiento est ON est.idestablecimiento = uest.establecimiento_idUbicacionEstablecimientos\n"
                + "INNER JOIN maquina maq ON est.idEstablecimiento = maq.Establecimiento_idEstablecimientos\n"
                + "INNER JOIN venta ven ON maq.idMaquina = ven.maquina_idMaquina\n"
                + "GROUP BY maq.idMaquina\n"
                + "ORDER BY venta desc; ");
        return query.getResultList();

    }

    @Override
    public List<Object[]> reporteMaquinasEstado() {
        Query query = em.createNativeQuery(" SELECT \n"
                + "      CASE\n"
                + "          WHEN ma.estado_mqna = 'D' THEN \"Reparación\"\n"
                + "          WHEN ma.estado_mqna = 'X' THEN \"Baja\"\n"
                + "          WHEN ma.estado_mqna = 'S' THEN \"Stock\"\n"
                + "          ELSE \"Activa\"\n"
                + "      END AS estado,\n"
                + "      coalesce(ma.motivo_estado_mqna,\"\") motivo,\n"
                + "      es.nombre_estbnto nomEst,z.nombre_ensbr as nomEnsam\n"
                + "FROM maquina ma\n"
                + "INNER JOIN establecimiento es ON ma.Establecimiento_idEstablecimientos = es.idEstablecimiento\n"
                + "inner join ensamblador z on ma.Ensamblador_idEnsamblador =z.idEnsamblador\n"
                + "GROUP BY ma.idMaquina, estado_mqna; ");
        return query.getResultList();

    }

    @Override
    public List<Object[]> reporteMaquinasEnsambla() {
        Query query = em.createNativeQuery(" SELECT en.idEnsamblador,\n"
                + "       en.nombre_ensbr,\n"
                + "       COUNT(ma.Ensamblador_idEnsamblador) AS MaquinasEnsambladas\n"
                + "FROM ensamblador en\n"
                + "INNER JOIN maquina ma ON ma.Ensamblador_idEnsamblador = en.idEnsamblador\n"
                + "GROUP BY nombre_ensbr\n"
                + "HAVING COUNT(ma.Ensamblador_idEnsamblador) >= 1;");
        return query.getResultList();

    }

    @Override
    public List<Object[]> reporteVentasRepartidor(int mes, int anio) {
        Query query = em.createNativeQuery("call reporteVentasRepartidor(?,?)");
        query.setParameter(1, mes);
        query.setParameter(2, anio);
        query.getResultList();
        return query.getResultList();

    }

    @Override
    public List<Object[]> reporteProdDevMaq() {
        Query query = em.createNativeQuery("SELECT motivo_estado_mqna,\n"
                + "       (((SELECT count(1)\n"
                + "            FROM maquina\n"
                + "            WHERE motivo_estado_mqna = 'Mal ensamblada')/((SELECT count(1)\n"
                + "                                                           FROM maquina\n"
                + "                                                           WHERE estado_mqna = 'D')))*100) AS prom\n"
                + "FROM maquina\n"
                + "WHERE motivo_estado_mqna = 'Mal ensamblada'\n"
                + "GROUP BY motivo_estado_mqna;");
        return query.getResultList();
    }

}
