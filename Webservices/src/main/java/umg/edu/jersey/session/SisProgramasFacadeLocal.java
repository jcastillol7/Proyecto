/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umg.edu.jersey.session;

import java.util.List;
import javax.ejb.Local;
import umg.edu.jersey.entity.SisProgramas;

/**
 *
 * @author mmavin
 */
@Local
public interface SisProgramasFacadeLocal {

    void create(SisProgramas sisProgramas);

    void edit(SisProgramas sisProgramas);

    void remove(SisProgramas sisProgramas);

    SisProgramas find(Object id);

    List<SisProgramas> findAll();

    List<SisProgramas> findRange(int[] range);

    int count();
    
}
