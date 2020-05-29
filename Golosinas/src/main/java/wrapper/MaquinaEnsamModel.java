/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.io.Serializable;

/**
 *
 * @author Marvin Moran
 */
public class MaquinaEnsamModel implements Serializable {

    private String ensamblador;
    private long numeroMaquinas;

    public String getEnsamblador() {
        return ensamblador;
    }

    public void setEnsamblador(String ensamblador) {
        this.ensamblador = ensamblador;
    }

    public long getNumeroMaquinas() {
        return numeroMaquinas;
    }

    public void setNumeroMaquinas(long numeroMaquinas) {
        this.numeroMaquinas = numeroMaquinas;
    }

}
