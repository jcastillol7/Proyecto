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
public class ListadoTiendaVent implements Serializable {

    private ArrayList<TiendaVentModel> reportTiendaVent;

    public ArrayList<TiendaVentModel> getReportTiendaVent() {
        return reportTiendaVent;
    }

    public void setReportTiendaVent(ArrayList<TiendaVentModel> reportTiendaVent) {
        this.reportTiendaVent = reportTiendaVent;
    }

}
