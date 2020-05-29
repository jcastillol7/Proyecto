/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Marvin Moran
 */
public class ListadoMinMes implements Serializable {

    private ArrayList<establecimientoModel> reporteMin;

    public ArrayList<establecimientoModel> getReporteMin() {
        return reporteMin;
    }

    public void setReporteMin(ArrayList<establecimientoModel> reporteMin) {
        this.reporteMin = reporteMin;
    }

}
