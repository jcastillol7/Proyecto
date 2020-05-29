/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Marvin Moran
 */
public class PromDevMaqModel implements Serializable{
    private String motEstMaq;
    private BigDecimal promedio;

    public String getMotEstMaq() {
        return motEstMaq;
    }

    public void setMotEstMaq(String motEstMaq) {
        this.motEstMaq = motEstMaq;
    }

    public BigDecimal getPromedio() {
        return promedio;
    }

    public void setPromedio(BigDecimal promedio) {
        this.promedio = promedio;
    }

   
    
    
}
