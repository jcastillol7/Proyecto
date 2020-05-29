package configuracion;

/**
 * Se utiliza para cambiar ruta del servidor al compilar
 *
 * @author Marvin Moran. Desarrollo Umg
 * For the glory of God
 */
public class Compilacion {

    private String direcCompile;
    private boolean desarrollo;
    private String paginaPrueba;

    /**
     * Metodo que se utiliza para cambiar la ruta del servidor al compilar.
     */
    public Compilacion() {

        // direcCompile ="http://192.168.10.200:8080";
        direcCompile = "";
        desarrollo = false;
        paginaPrueba = "mantenimientos/mnt_alumnos_web.xhtml";

    }

    public String getDirecCompile() {
        return direcCompile;
    }

    public void setDirecCompile(String direcCompile) {
        this.direcCompile = direcCompile;
    }

    public boolean getDesarrollo() {
        return desarrollo;
    }

    public void setDesarrollo(boolean desarrollo) {
        this.desarrollo = desarrollo;
    }

    public String getPaginaPrueba() {
        return paginaPrueba;
    }

    public void setPaginaPrueba(String paginaPrueba) {
        this.paginaPrueba = paginaPrueba;
    }

}
