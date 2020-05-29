/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author mmavin
 */
public class Decryptor {

    public Decryptor() {

    }

    public String decriptor(String cadenaEncriptada, int ini, int ran) {
        String cadena = "";
        if (!cadenaEncriptada.equals("null")) {
            try{
            int len = Integer.parseInt(cadenaEncriptada.substring(ini, ini + 2));
            int rango = ran;
            int inicio = ini + 2 + rango;
            for (int i = 1; i <= len; i++) {
                cadena = cadena + cadenaEncriptada.substring(inicio, inicio + 1);
                inicio = inicio + ran + 1;

            }
            }catch(java.lang.StringIndexOutOfBoundsException e){
                cadena="";
            }
        }

        return cadena;
    }

}
