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
public class ListadoMaquinasEstadoModel implements Serializable {

    private ArrayList<MaquinaEstadoModel> reporteTiendaEst;

    public ArrayList<MaquinaEstadoModel> getReporteTiendaEst() {
        return reporteTiendaEst;
    }

    public void setReporteTiendaEst(ArrayList<MaquinaEstadoModel> reporteTiendaEst) {
        this.reporteTiendaEst = reporteTiendaEst;
    }

}
