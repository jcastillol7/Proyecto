package clases;

/**
 * Sirve para simular un Padl
 *
 * @author Marvin Moran. Desarrollo Umg
 */
public class Padl {

    public Padl() {
    }

    public String getFullString(String cadena, String prefijo, int longitudMaxima) {

        StringBuffer res = new StringBuffer();
        for (int i = 0; i < (longitudMaxima - cadena.trim().length()); i++) {
            res.append(prefijo);
        }
        res.append(cadena);
        return res.toString();
    }

    /**
     * Metodo que se encarga en agregar los espacios para el carnet
     *
     * @param cadena Recibe la cadena
     * @param prefijo Pone el caracter que vamos a llenar
     * @param longitudMaxima Recibe el tamano en el cual vamos a utilizar
     * @return
     */
    public String Padl(String cadena, String prefijo, int longitudMaxima) {
        String resultado = "";
        cadena = cadena.trim();
        String Prefx = "";
        for (int i = 0; i < longitudMaxima - cadena.trim().length(); i++) {
            Prefx = Prefx + prefijo;
        }
        resultado = Prefx + cadena;
        return resultado;

    }
    
     public String Radl(String cadena, String prefijo, int longitudMaxima) {
        String resultado = "";
        cadena=cadena.trim();
        String Prefx = "";
        for (int i = 0; i < longitudMaxima - cadena.trim().length(); i++) {
            Prefx = Prefx + prefijo;
        }
        resultado = cadena+Prefx;
        return resultado;

    }
}
