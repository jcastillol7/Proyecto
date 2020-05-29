/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umg.edu.jersey.session;

import java.util.List;
import javax.ejb.Local;
import umg.edu.jersey.entity.SisAccesos;

/**
 *
 * @author mmavin
 */
@Local
public interface SisAccesosFacadeLocal {

    void create(SisAccesos sisAccesos);

    void edit(SisAccesos sisAccesos);

    void remove(SisAccesos sisAccesos);

    SisAccesos find(Object id);

    List<SisAccesos> findAll();

    List<SisAccesos> findRange(int[] range);

    int count();

    public List<Object[]> consultaAccesos(String usuario);

    public List<Object[]> reporteMay(int ini, int fin, int anio);

    public List<Object[]> buscaLogin(String usuario, String clave);

    public List<Object[]> reporteMin(int ini, int fin, int anio);

    public List<Object[]> reporteComercioMen(int mes, String tipo, int anio);

    public List<Object[]> reporteTienda(int id);

    public List<Object[]> reporteMaquinasEstado();

    public List<Object[]> reporteMaquinasEnsambla();

    public List<Object[]> reporteVentasRepartidor(int mes, int anio);

    public List<Object[]> reporteProdDevMaq();

 
    
}
