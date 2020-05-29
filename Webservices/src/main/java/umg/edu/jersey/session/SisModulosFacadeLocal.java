/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umg.edu.jersey.session;

import java.util.List;
import javax.ejb.Local;
import umg.edu.jersey.entity.SisModulos;

/**
 *
 * @author mmavin
 */
@Local
public interface SisModulosFacadeLocal {

    void create(SisModulos sisModulos);

    void edit(SisModulos sisModulos);

    void remove(SisModulos sisModulos);

    SisModulos find(Object id);

    List<SisModulos> findAll();

    List<SisModulos> findRange(int[] range);

    int count();
    
}
