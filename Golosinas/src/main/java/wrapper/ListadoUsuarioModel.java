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
 * @author Josue
 */
public class ListadoUsuarioModel implements Serializable{
    private ArrayList<UsuarioModel> accesos;

    public ArrayList<UsuarioModel> getAccesos() {
        return accesos;
    }

    public void setAccesos(ArrayList<UsuarioModel> accesos) {
        this.accesos = accesos;
    }
    
}
