package configuracion;

/**
 * Usuarios Bean se encarga de manejar el ingreso al Sistema y crear el menu
 * dinamico, tambien se encarga de abrir y cerrar la sesion
 *
 * @author Marvin Moran
 */
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;

//import session.CUsuariosFacadeLocal;

@SessionScoped
@Named(value = "UsuariosBeanNew")
public class UsuariosBeanNew implements Serializable {

    private String usuario;
    private String contrasena;
    private boolean logeado = false;
    private List<Object[]> SisAccesos;
    private String page;
    private static String titulo;
    private MenuModel modelSol;
    private boolean muestraSol;
    private MenuModel modelSecurity;
    private boolean muestraSecurity;
    private MenuModel modelProp;
    private boolean muestraProp;
    private MenuModel modelEstadisticas;
    private boolean muestraEstadisticas;
    private MenuModel modelContrasenas;
    private boolean muestraContrasenas;
    private String tituloMenu;
    private String grupo;
    private int idGrupo;
//    @EJB
   // private CUsuariosFacadeLocal sisusuarios;
   

    public UsuariosBeanNew() {
        muestraSol = false;
        muestraSecurity = false;
        muestraProp = false;
        muestraEstadisticas = false;
        muestraContrasenas = false;
    }

    /**
     * Este metodo se utiliza para dar o no acceso al usuario al sistema
     */
    public void buscaLogin() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
      //  logeado = sisusuarios.SisUsuariosLogin(usuario, contrasena);
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido Al Sistema Acad \n" + "\n" + usuario, "");
        if (logeado) {
            logeado = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
           

        } else {
            logeado = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario o Contraseña Incorrectas", " ");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("estaLogeado", logeado);
        if (logeado) {
            Compilacion config = new Compilacion();
            config.getDirecCompile();
            CreaMenu();
            context.addCallbackParam("view", config.getDirecCompile() + "/Golosinas/umg/index.xhtml");
            setEstaLogueado(true);
        }
    }

    /**
     * Metodo que se encarga de salir de la sesion y eliminarla
     *
     * @throws IOException
     */
    public void logout() throws IOException {
        logeado = false;
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/umg/");
    }

    public void CreaMenu() {
       /* SisAccesos = sisusuarios.SisAccesosNatGen(usuario);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("SisAccesos", SisAccesos);
        CreaMenus(SisAccesos);*/
    }

    /**
     * Metodo donse se van generando los modulos para el menu
     */
    public void CreaMenus(List<Object[]> SisAccesos) {
        //Security
        modelSecurity = new DefaultMenuModel();
        DefaultSubMenu SubmenuManteSecurity = new DefaultSubMenu("Mantenimientos");
        DefaultSubMenu SubmenuConsulSecurity = new DefaultSubMenu("Consultas");
        DefaultSubMenu SubmenuReportSecurity = new DefaultSubMenu("Reportes");
        DefaultSubMenu SubmenuMovSecurity = new DefaultSubMenu("Movimientos");
        //Sol99
        modelSol = new DefaultMenuModel();
        DefaultSubMenu SubmenuManteSol = new DefaultSubMenu("Mantenimientos");
        DefaultSubMenu SubmenuConsulSol = new DefaultSubMenu("Consultas");
        DefaultSubMenu SubmenuReportSol = new DefaultSubMenu("Reportes");
        DefaultSubMenu SubmenuMovSol = new DefaultSubMenu("Movimientos");
        //Propuestas
        modelProp = new DefaultMenuModel();
        DefaultSubMenu SubmenuManteProp = new DefaultSubMenu("Mantenimientos");
        DefaultSubMenu SubmenuConsulProp = new DefaultSubMenu("Consultas");
        DefaultSubMenu SubmenuReportProp = new DefaultSubMenu("Reportes");
        DefaultSubMenu SubmenuMovProp = new DefaultSubMenu("Movimientos");
        //Estadisiticas
        modelEstadisticas = new DefaultMenuModel();
        DefaultSubMenu SubmenuManteEstad = new DefaultSubMenu("Mantenimientos");
        DefaultSubMenu SubmenuConsulEstad = new DefaultSubMenu("Consultas");
        DefaultSubMenu SubmenuReportEstad = new DefaultSubMenu("Reportes");
        DefaultSubMenu SubmenuMovEstad = new DefaultSubMenu("Movimientos");
        //Contrasenas
        modelContrasenas = new DefaultMenuModel();
        DefaultSubMenu SubmenuManteContra = new DefaultSubMenu("Mantenimientos");
        DefaultSubMenu SubmenuConsulContra = new DefaultSubMenu("Consultas");
        DefaultSubMenu SubmenuReportContra = new DefaultSubMenu("Reportes");
        DefaultSubMenu SubmenuMovContra = new DefaultSubMenu("Movimientos");

        if (SisAccesos.size() > 0 || SisAccesos != null) {
            for (Object[] result : SisAccesos) {
                //Seguridad
                if (result[4].toString().matches("1")) {
                    if (result[2].toString().matches("M")) {
                        DefaultMenuItem item1 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/mantenimientos/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item1.setUrl(ruta);
                        SubmenuManteSecurity.addElement(item1);
                    }
                    if (result[2].toString().matches("C")) {
                        DefaultMenuItem item2 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/consultas/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item2.setUrl(ruta);
                        SubmenuConsulSecurity.addElement(item2);
                    }
                    if (result[2].toString().matches("R")) {
                        DefaultMenuItem item3 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/reportes/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item3.setUrl(ruta);
                        SubmenuReportSecurity.addElement(item3);
                    }
                    if (result[2].toString().matches("V")) {
                        DefaultMenuItem item4 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/movimientos/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item4.setUrl(ruta);
                        SubmenuMovSecurity.addElement(item4);
                    }
                    muestraSecurity = true;
                }
                //Sol99
                if (result[4].toString().matches("2")) {
                    if (result[2].toString().matches("M")) {
                        DefaultMenuItem item1 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/mantenimientos/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item1.setUrl(ruta);
                        SubmenuManteSol.addElement(item1);
                    }
                    if (result[2].toString().matches("C")) {
                        DefaultMenuItem item2 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/consultas/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item2.setUrl(ruta);
                        SubmenuConsulSol.addElement(item2);
                    }
                    if (result[2].toString().matches("R")) {
                        DefaultMenuItem item3 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/reportes/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item3.setUrl(ruta);
                        SubmenuReportSol.addElement(item3);
                    }
                    if (result[2].toString().matches("V")) {
                        DefaultMenuItem item4 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/movimientos/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item4.setUrl(ruta);
                        SubmenuMovSol.addElement(item4);
                    }
                    muestraSol = true;
                }
                //Propuestas
                if (result[4].toString().matches("7")) {
                    if (result[2].toString().matches("M")) {
                        DefaultMenuItem item1 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/mantenimientos/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item1.setUrl(ruta);
                        SubmenuManteProp.addElement(item1);
                    }
                    if (result[2].toString().matches("C")) {
                        DefaultMenuItem item2 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/consultas/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item2.setUrl(ruta);
                        SubmenuConsulProp.addElement(item2);
                    }
                    if (result[2].toString().matches("R")) {
                        DefaultMenuItem item3 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/reportes/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item3.setUrl(ruta);
                        SubmenuReportProp.addElement(item3);
                    }
                    if (result[2].toString().matches("V")) {
                        DefaultMenuItem item4 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/movimientos/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item4.setUrl(ruta);
                        SubmenuMovProp.addElement(item4);
                    }
                    muestraProp = true;
                }
                //Estadisticas
                if (result[4].toString().matches("12")) {
                    if (result[2].toString().matches("M")) {
                        DefaultMenuItem item1 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/mantenimientos/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item1.setUrl(ruta);
                        SubmenuManteEstad.addElement(item1);
                    }
                    if (result[2].toString().matches("C")) {
                        DefaultMenuItem item2 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/consultas/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item2.setUrl(ruta);
                        SubmenuConsulEstad.addElement(item2);
                    }
                    if (result[2].toString().matches("R")) {
                        DefaultMenuItem item3 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/reportes/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item3.setUrl(ruta);
                        SubmenuReportEstad.addElement(item3);
                    }
                    if (result[2].toString().matches("V")) {
                        DefaultMenuItem item4 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/movimientos/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item4.setUrl(ruta);
                        SubmenuMovEstad.addElement(item4);
                    }
                    muestraEstadisticas = true;
                }
                //Ontrasenas
                if (result[4].toString().matches("5")) {
                    if (result[2].toString().matches("M")) {
                        DefaultMenuItem item1 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/mantenimientos/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item1.setUrl(ruta);
                        SubmenuManteContra.addElement(item1);
                    }
                    if (result[2].toString().matches("C")) {
                        DefaultMenuItem item2 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/consultas/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item2.setUrl(ruta);
                        SubmenuConsulContra.addElement(item2);
                    }
                    if (result[2].toString().matches("R")) {
                        DefaultMenuItem item3 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/reportes/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item3.setUrl(ruta);
                        SubmenuReportContra.addElement(item3);
                    }
                    if (result[2].toString().matches("V")) {
                        DefaultMenuItem item4 = new DefaultMenuItem(result[0]);
                        String nomprog = result[1] + ".xhtml";
                        String titulo = (String) result[0];
                        String ruta = "/umg/" + result[5].toString().trim().toLowerCase() + "/movimientos/" + nomprog;
                        if (!Compara(ruta)) {
                            ruta = "/umg/Construccion.xhtml";
                        }
                        item4.setUrl(ruta);
                        SubmenuMovContra.addElement(item4);
                    }
                    muestraContrasenas = true;
                }
            }
            //Seguridad
            modelSecurity.addElement(SubmenuManteSecurity);
            modelSecurity.addElement(SubmenuConsulSecurity);
            modelSecurity.addElement(SubmenuReportSecurity);
            modelSecurity.addElement(SubmenuMovSecurity);
            //Sol99
            modelSol.addElement(SubmenuManteSol);
            modelSol.addElement(SubmenuConsulSol);
            modelSol.addElement(SubmenuReportSol);
            modelSol.addElement(SubmenuMovSol);
            //Prpuestas
            modelProp.addElement(SubmenuManteProp);
            modelProp.addElement(SubmenuConsulProp);
            modelProp.addElement(SubmenuReportProp);
            modelProp.addElement(SubmenuMovProp);
            //Estadisticas
            modelEstadisticas.addElement(SubmenuManteEstad);
            modelEstadisticas.addElement(SubmenuConsulEstad);
            modelEstadisticas.addElement(SubmenuReportEstad);
            modelEstadisticas.addElement(SubmenuMovEstad);
            //Contrasenas
            modelContrasenas.addElement(SubmenuManteContra);
            modelContrasenas.addElement(SubmenuConsulContra);
            modelContrasenas.addElement(SubmenuReportContra);
            modelContrasenas.addElement(SubmenuMovContra);
        }

    }

    public List<Object[]> getSisAccesos() {
        return SisAccesos;
    }

    public void setSisAccesos(List<Object[]> SisAccesos) {
        this.SisAccesos = SisAccesos;
    }

    public boolean getEstaLogeado() {
        return logeado;
    }

    public void setEstaLogueado(boolean logeado) {
        this.logeado = logeado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario.toUpperCase();
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        if (!Compara(page.trim())) {
            page = "Construccion.xhtml";
        }
        this.page = page;
        // GeneraMenus();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo, List<Object[]> SisAccesosS) {
        String Titulo = "";
        if (titulo.length() > 0 && titulo != null && !titulo.equals("")) {
            if (titulo.indexOf("/") != 0) {
                int pos = titulo.lastIndexOf("/");
                String cad1 = titulo.substring(pos + 1, titulo.length());
                if (cad1.length() > 0) {
                    String cad2 = cad1.substring(0, cad1.indexOf("."));
                    if (SisAccesosS != null) {
                        for (Object[] result : SisAccesosS) {
                            if (result[1].toString().matches(cad2)) {
                                Titulo = (String) result[0];
                            }
                        }

                    }
                }
            }
        }

        this.titulo = Titulo;

    }

    public List<String> getXhtmls() {
        return getResources("/", ".xhtml");
    }

    /**
     * Trae en un list Las paginas Existentes
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

    /**
     * Compara si la pagina existe o No
     *
     * @param page
     * @return
     */
    public boolean Compara(String page) {
        List<String> xhtmls = getXhtmls();
        return xhtmls.contains(page);
    }

    public MenuModel getModelSol() {
        return modelSol;
    }

    public void setModelSol(MenuModel modelSol) {
        this.modelSol = modelSol;
    }

    public MenuModel getModelSecurity() {
        return modelSecurity;
    }

    public void setModelSecurity(MenuModel modelSecurity) {
        this.modelSecurity = modelSecurity;
    }

    public MenuModel getModelProp() {
        return modelProp;
    }

    public void setModelProp(MenuModel modelProp) {
        this.modelProp = modelProp;
    }

    public boolean isMuestraSol() {
        return muestraSol;
    }

    public void setMuestraSol(boolean muestraSol) {
        this.muestraSol = muestraSol;
    }

    public boolean isMuestraSecurity() {
        return muestraSecurity;
    }

    public void setMuestraSecurity(boolean muestraSecurity) {
        this.muestraSecurity = muestraSecurity;
    }

    public boolean isMuestraProp() {
        return muestraProp;
    }

    public void setMuestraProp(boolean muestraProp) {
        this.muestraProp = muestraProp;
    }

    public MenuModel getModelEstadisticas() {
        return modelEstadisticas;
    }

    public void setModelEstadisticas(MenuModel modelEstadisticas) {
        this.modelEstadisticas = modelEstadisticas;
    }

    public boolean isMuestraEstadisticas() {
        return muestraEstadisticas;
    }

    public void setMuestraEstadisticas(boolean muestraEstadisticas) {
        this.muestraEstadisticas = muestraEstadisticas;
    }

    public String getTituloMenu() {
        return tituloMenu;
    }

    public void setTituloMenu(String tituloMenu) {
        this.tituloMenu = tituloMenu;
    }

    public MenuModel getModelContrasenas() {
        return modelContrasenas;
    }

    public void setModelContrasenas(MenuModel modelContrasenas) {
        this.modelContrasenas = modelContrasenas;
    }

    public boolean isMuestraContrasenas() {
        return muestraContrasenas;
    }

    public void setMuestraContrasenas(boolean muestraContrasenas) {
        this.muestraContrasenas = muestraContrasenas;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

}
