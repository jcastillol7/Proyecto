package configuracion;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 * Genera una ruta aleatoria de una imagen
 *
 * @author Marvin Moran. Desarrollo umg
 */
@Named
@javax.faces.view.ViewScoped
public class GalleriaBean implements Serializable {

    private List<String> images;
    private String foto;
    private String tituloMenu;
    private String direccion;
  
    public void redir() throws IOException{
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/umg/");
    }
    /**
     * Metodo que se utiliza para mostrar una imagen aleatoria.
     */
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 10; i++) {
            images.add("imagen" + i + ".jpg");
        }
        Random random = new Random();
        int num = random.nextInt(10);
        foto = images.get(num);
    }

    public List<String> getImages() {
        return images;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTituloMenu() {
        return tituloMenu;
    }

    public void setTituloMenu(String tituloMenu) {
        this.tituloMenu = tituloMenu;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
