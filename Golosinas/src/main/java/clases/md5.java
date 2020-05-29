///Clase que Genera el Md5
package clases;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Genera un md5
 *
 * @author Marvin Moran. Desarrollo Umg
 */
public class md5 {

    private static final char[] CONSTS_HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * Metodo que devuelve la cadena encriptada en md5
     *
     * @param stringAEncriptar devuelve el String cadena Encriptada
     * @return
     */
    public static String encriptaEnMD5(String stringAEncriptar) {
        try {
            MessageDigest msgd = MessageDigest.getInstance("MD5");
            byte[] bytes = msgd.digest(stringAEncriptar.getBytes());
            StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
            for (int i = 0; i < bytes.length; i++) {
                int bajo = (int) (bytes[i] & 0x0f);
                int alto = (int) ((bytes[i] & 0xf0) >> 4);
                strbCadenaMD5.append(CONSTS_HEX[alto]);
                strbCadenaMD5.append(CONSTS_HEX[bajo]);
            }
            return strbCadenaMD5.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
