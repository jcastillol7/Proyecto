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
public class FichaComercioModel implements Serializable{
    
    private String establecimiento;
    private String direccion;
    private String correo;
    private BigDecimal total;
    private String estadop;

    public FichaComercioModel(String establecimiento, String direccion, String correo, BigDecimal total, String estadop) {
        this.establecimiento = establecimiento;
        this.direccion = direccion;
        this.correo = correo;
        this.total = total;
        this.estadop= estadop;
    }

    
    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstadop() {
        return estadop;
    }

    public void setEstadop(String estadop) {
        this.estadop = estadop;
    }

    

    
    
    
    
}
