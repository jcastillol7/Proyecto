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
public class TiendaVentModel implements Serializable {

    public int maquina;
    public String establecimiento;
    public String direccion;
    public long prodVendidos;
    public BigDecimal venta;

    public int getMaquina() {
        return maquina;
    }

    public void setMaquina(int maquina) {
        this.maquina = maquina;
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

    public long getProdVendidos() {
        return prodVendidos;
    }

    public void setProdVendidos(long prodVendidos) {
        this.prodVendidos = prodVendidos;
    }

    public BigDecimal getVenta() {
        return venta;
    }

    public void setVenta(BigDecimal venta) {
        this.venta = venta;
    }

}
