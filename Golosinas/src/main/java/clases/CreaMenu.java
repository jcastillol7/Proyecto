package clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import wrapper.UsuarioModel;

/**
 * Genera el menu dinamico, utilizando el metodo CreaMenus
 *
 * @author Marvin Moran. Desarrollo Umg
 */
public class CreaMenu {

    private int countAccesos;

    public CreaMenu() {

    }

    /**
     * Este metodo genera el menu dinamico deacuerdo a los modulos que vamos a
     * crear, retornara el menumodel lleno.
     *
     * @param SisAccesos Este parametro recibe la coleccion con los accesos que
     * tiene el usuario
     * @param modulo Se envia el id del mudulo a crear
     * @param model se envia el menumodel vacio, el cual llenara al pasar por
     * este metod
     * @return
     */
    public MenuModel CreaMenus(List<UsuarioModel> listadoAccesos, String modulo, MenuModel model) {
        countAccesos = 0;
        DefaultSubMenu SubmenuMante = new DefaultSubMenu("Mantenimientos");
        if (listadoAccesos.size() > 0 || listadoAccesos != null) {
            for (UsuarioModel result : listadoAccesos) {
                if (result.getTipo().matches("M")) {
                    if (String.valueOf(result.getIdModulo()).matches(modulo)) {

                        DefaultMenuItem item1 = new DefaultMenuItem(result.getTitulo());
                        System.out.println("Titulo " + result.getTitulo());
                        String nomprog = result.getNombrePrograma() + ".xhtml";
                        String titulo = result.getTitulo();
                        String ruta = "/umg/" + result.getModulo().toLowerCase() + "/mantenimientos/" + nomprog;
                        item1.setUrl(ruta);
                        SubmenuMante.addElement(item1);
                    }
                    countAccesos++;
                }

            }
            model.addElement(SubmenuMante);
        }

        DefaultSubMenu SubmenuConsul = new DefaultSubMenu("Consultas");
        if (listadoAccesos.size() > 0 || listadoAccesos != null) {
            for (UsuarioModel result : listadoAccesos) {
                if (result.getTipo().matches("C")) {
                    if (String.valueOf(result.getIdModulo()).matches(modulo)) {

                        DefaultMenuItem item1 = new DefaultMenuItem(result.getTitulo());
                        System.out.println("Titulo " + result.getTitulo());
                        String nomprog = result.getNombrePrograma() + ".xhtml";
                        String titulo = result.getTitulo();
                        String ruta = "/umg/" + result.getModulo().toLowerCase() + "/consultas/" + nomprog;
                        item1.setUrl(ruta);
                        SubmenuConsul.addElement(item1);
                    }
                    countAccesos++;
                }

            }
            model.addElement(SubmenuConsul);

        }
        
         DefaultSubMenu SubmenuMov = new DefaultSubMenu("Movimientos");
        if (listadoAccesos.size() > 0 || listadoAccesos != null) {
            for (UsuarioModel result : listadoAccesos) {
                if (result.getTipo().matches("V")) {
                    if (String.valueOf(result.getIdModulo()).matches(modulo)) {

                        DefaultMenuItem item1 = new DefaultMenuItem(result.getTitulo());
                        System.out.println("Titulo " + result.getTitulo());
                        String nomprog = result.getNombrePrograma() + ".xhtml";
                        String titulo = result.getTitulo();
                        String ruta = "/umg/" + result.getModulo().toLowerCase() + "/movimientos/" + nomprog;
                        item1.setUrl(ruta);
                        SubmenuMov.addElement(item1);
                    }
                    countAccesos++;
                }

            }
            model.addElement(SubmenuMov);

        }
        return model;
    }

    /**
     * Compara si la pagina Existe o No
     *
     * @param page recibe el nombre de la pagina
     * @return
     */
    public boolean Compara(String page) {
        List<String> xhtmls = getXhtmls();
        return xhtmls.contains(page);
    }

    public List<String> getXhtmls() {
        return getResources("/", ".xhtml");
    }

    /**
     * Trae un List, con las paginas Existenetes
     *
     * @param path
     * @param suffix
     * @return
     */
    protected List<String> getResources(String path, String suffix) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Set<String> resources = context.getResourcePaths(path);
        List<String> filteredResources = new ArrayList<String>();
        for (String resource : resources) {
            if (resource.endsWith(suffix)) {
                filteredResources.add(resource);
            } else if (resource.endsWith("/")) {
                filteredResources.addAll(getResources(resource, suffix));
            }
        }
        return filteredResources;
    }

    public void save() {
        addMessage("Success", "Data saved");
        System.out.println("xxxxx");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public int getCountAccesos() {
        return countAccesos;
    }

    public void setCountAccesos(int countAccesos) {
        this.countAccesos = countAccesos;
    }

}
