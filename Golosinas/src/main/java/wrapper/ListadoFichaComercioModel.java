/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.io.Serializable;
import java.util.ArrayList;

/**
 
 * @author Marvin Moran
 */
public class ListadoFichaComercioModel implements Serializable{
    
     private ArrayList<FichaComercioModel> reporteComercioMen;

    public ArrayList<FichaComercioModel> getReporteComercioMen() {
        return reporteComercioMen;
    }

    public void setReporteComercioMen(ArrayList<FichaComercioModel> reporteComercioMen) {
        this.reporteComercioMen = reporteComercioMen;
    }
     
     
    
}
