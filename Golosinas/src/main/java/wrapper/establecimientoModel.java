/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

/**
 *
 * @author Josue
 */
public class establecimientoModel {
      private String establecimiento;
        private int maquina;
        private String direccion;
        private float venta;
        private float gananciaEstablecimiento;
        private float diferencia;

    public establecimientoModel() {
    }

    public establecimientoModel(String establecimiento, int maquina, String direccion, float venta, float gananciaEstablecimiento, float diferencia) {
        this.establecimiento = establecimiento;
        this.maquina = maquina;
        this.direccion = direccion;
        this.venta = venta;
        this.gananciaEstablecimiento = gananciaEstablecimiento;
        this.diferencia = diferencia;
    }

    
        
    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public int getMaquina() {
        return maquina;
    }

    public void setMaquina(int maquina) {
        this.maquina = maquina;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public float getVenta() {
        return venta;
    }

    public void setVenta(float venta) {
        this.venta = venta;
    }

    public float getGananciaEstablecimiento() {
        return gananciaEstablecimiento;
    }

    public void setGananciaEstablecimiento(float gananciaEstablecimiento) {
        this.gananciaEstablecimiento = gananciaEstablecimiento;
    }

    public float getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(float diferencia) {
        this.diferencia = diferencia;
    }
        
        
    
}
