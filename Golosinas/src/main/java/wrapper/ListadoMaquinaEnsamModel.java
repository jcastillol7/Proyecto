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
public class ListadoMaquinaEnsamModel implements Serializable {
     private ArrayList<MaquinaEnsamModel> reporteMaquinasEnsam;

    public ArrayList<MaquinaEnsamModel> getReporteMaquinasEnsam() {
        return reporteMaquinasEnsam;
    }

    public void setReporteMaquinasEnsam(ArrayList<MaquinaEnsamModel> reporteMaquinasEnsam) {
        this.reporteMaquinasEnsam = reporteMaquinasEnsam;
    }
     
    
    
}
