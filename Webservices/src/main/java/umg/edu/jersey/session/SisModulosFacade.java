/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umg.edu.jersey.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import umg.edu.jersey.entity.SisModulos;

/**
 *
 * @author mmavin
 */
@Stateless
public class SisModulosFacade extends AbstractFacade<SisModulos> implements SisModulosFacadeLocal {

    @PersistenceContext(unitName = "persistencia")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SisModulosFacade() {
        super(SisModulos.class);
    }
    
}
