/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umg.edu.jersey.session;

import java.util.List;
import javax.ejb.Local;
import umg.edu.jersey.entity.SisUsuarios;

/**
 *
 * @author mmavin
 */
@Local
public interface SisUsuariosFacadeLocal {

    void create(SisUsuarios sisUsuarios);

    void edit(SisUsuarios sisUsuarios);

    void remove(SisUsuarios sisUsuarios);

    SisUsuarios find(Object id);

    List<SisUsuarios> findAll();

    List<SisUsuarios> findRange(int[] range);

    int count();
    
}
