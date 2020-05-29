/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;

/**
 * Generar el Algoritmo de la contraseña
 *
 * @author Marvin Moran. Desarrollo Umg
 */
public class GeneraClave implements Serializable {

    private String cClave;

    public GeneraClave() {

    }

    public GeneraClave(String Clave) {
        cClave = Clave;
    }

    /**
     * Metodo que se encarga de generar el algoritmo para la clave
     *
     * @return
     */
    public String CreaClave() {
        String sclave = "";
        int NumAsc = 0;
        String Replica = "";
        double numero = cClave.length();
        char c = cClave.charAt(0);
        NumAsc = (int) c;
        for (int i = 0; i < numero; i++) {
            Replica = Replica + NumAsc;
        }
        //md5 clavemd = new md5();
        String x = md5.encriptaEnMD5(cClave.substring(2));
        sclave = cClave.substring(0, 2) + NumAsc + x.substring(0, 4);
        return sclave;
    }

}
