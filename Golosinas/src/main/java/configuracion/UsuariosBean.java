package configuracion;

/**
 * Usuarios Bean se encarga de manejar el ingreso al Sistema y crear el menu
 * dinamico, tambien se encarga de abrir y cerrar la sesion
 *
 * @author Marvin Moran
 */
import clases.Const;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import clases.CreaMenu;
import clases.Decryptor;
import clases.WebServices;
import clases.md5;
import javax.servlet.http.HttpServletRequest;
import wrapper.UsuarioModel;

import wrapper.ListadoLoginModel;
import wrapper.ListadoUsuarioModel;
import wrapper.LoginModel;
import wrapper.TokenModel;

@SessionScoped
@Named(value = "UsuariosBean")
public class UsuariosBean implements Serializable {

    private String usuario;
    private String contrasena;
    private boolean logeado = false;
   
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
    private int idUsuario;
    private boolean estado;
    private ListadoLoginModel arrayDeJson;
    private TokenModel token;
    private List<LoginModel> listadoLogin;
    private List<UsuarioModel> listadoAccesos;

    public UsuariosBean() {
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
        logeado = consumidor();
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido Al Sistema Golosinas \n" + "\n" + usuario, "");
        if (logeado) {
            logeado = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);

        } else {
            logeado = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario ó Contraseña Incorrectas", " ");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("estaLogeado", logeado);
        if (logeado) {
            Compilacion config = new Compilacion();
            config.getDirecCompile();
            
            context.addCallbackParam("view", config.getDirecCompile() + "/Golosinas/umg/index.xhtml");
            setEstaLogueado(true);
            this.titulo = " ";
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

    
    /**
     * Metodo donse se van generando los modulos para el menu
     */
    public void CreaMenu(List<UsuarioModel> listAccesos) {

        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("SisAccesos", listAccesos);
        CreaMenu creaMenu = new CreaMenu();
      
        //modulo Administrativo
        modelSol = new DefaultMenuModel();
        modelSol = creaMenu.CreaMenus(listAccesos, "2", modelSol);/////////////////////////////////////////////////////////////
        if (creaMenu.getCountAccesos() > 0) {
            muestraSol = true;
        }
        
        //modulo Sol99
        modelSecurity = new DefaultMenuModel();
        modelSecurity = creaMenu.CreaMenus(listAccesos, "1", modelSecurity);/////////////////////////////////////////////////////////////
        if (creaMenu.getCountAccesos() > 0) {
            muestraSecurity = true;
        }

       
    }

    public boolean consumidor() {
        try {
            
            String clave = "";
            //showTabla = false;
            WebServices services = new WebServices();
            //obtenerToken
            String[][] parametros = new String[5][2];
            parametros[0][0] = "username";
            parametros[0][1] = "pagina_web";
            parametros[1][0] = "password";
            parametros[1][1] = "Universidad$";
            parametros[2][0] = "client_id";
            parametros[2][1] = "web-movil-td";
            parametros[3][0] = "client_secret";
            parametros[3][1] = "ABXCDFRREEZXCV";
            parametros[4][0] = "grant_type";
            parametros[4][1] = "password";
            token = new TokenModel();
            token = (TokenModel) services.consumidorDeRecurso(parametros, "http://"+Const.rutaServ+":8080/Ws-oauth2-gs/oauth/token", token);
            System.out.println(token.getAccessToken());

            //obtenerUsuarios
            contrasena = md5.encriptaEnMD5(contrasena.trim().toUpperCase());
            clave = contrasena.trim();
            parametros = new String[2][2];
            parametros[0][0] = "usuario";
            parametros[0][1] = usuario.toUpperCase().trim();
            parametros[1][0] = "clave";
            parametros[1][1] = clave;

            arrayDeJson = new ListadoLoginModel();
            arrayDeJson = (ListadoLoginModel) services.consumidorDerecursoPorTokenGet(parametros, "http://"+Const.rutaServ+":8080/Ws-oauth2-gs/resources/loginGolosinas",
                    "Bearer " + token.getAccessToken(), arrayDeJson);
            listadoLogin = new ArrayList<>();
            listadoLogin = arrayDeJson.getLogin();
            if (token != null && listadoLogin.size() > 0 && listadoLogin != null) {
                estado = true;
                parametros = new String[1][2];
                parametros[0][0] = "usuario";
                parametros[0][1] = usuario;
                ListadoUsuarioModel arrayDeJsonAcc = new ListadoUsuarioModel();
                arrayDeJsonAcc = (ListadoUsuarioModel) services.consumidorDerecursoPorTokenGet(parametros, "http://"+Const.rutaServ+":8080/Ws-oauth2-gs/resources/accesos",
                        "Bearer " + token.getAccessToken(), arrayDeJsonAcc);
                listadoAccesos = arrayDeJsonAcc.getAccesos();
                CreaMenu(listadoAccesos);
                System.out.println("accesos"+listadoAccesos.size());

            } else {

                estado = false;
            }
        } catch (Exception e) {
            e.printStackTrace();

            estado = false;
        }
        return estado;
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

    public void setTitulo(String titulo, List<UsuarioModel> SisAccesosS) {
        this.titulo = "";
        String Titulo = "";
        if (titulo.length() > 0 && titulo != null && !titulo.equals("")) {
            if (titulo.indexOf("/") != 0) {
                int pos = titulo.lastIndexOf("/");
                String cad1 = titulo.substring(pos + 1, titulo.length());
                if (cad1.length() > 0) {
                    String cad2 = cad1.substring(0, cad1.indexOf("."));
                    if (SisAccesosS != null) {
                        for (UsuarioModel result : SisAccesosS) {
                            if (result.getNombrePrograma().matches(cad2)) {
                                Titulo = result.getTitulo();
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
