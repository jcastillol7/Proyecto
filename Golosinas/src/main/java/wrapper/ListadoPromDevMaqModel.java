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
public class ListadoPromDevMaqModel implements Serializable {
    
   private ArrayList<PromDevMaqModel> reporteProDevMaq;

    public ArrayList<PromDevMaqModel> getReporteProDevMaq() {
        return reporteProDevMaq;
    }

    public void setReporteProDevMaq(ArrayList<PromDevMaqModel> reporteProDevMaq) {
        this.reporteProDevMaq = reporteProDevMaq;
    }
   
    
}
