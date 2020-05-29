package filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import configuracion.Compilacion;
import configuracion.UsuariosBean;
import configuracion.UsuariosBeanNew;
import javax.servlet.http.HttpSession;
import wrapper.UsuarioModel;

/**
 * Esta clase es la encargada de la seguridad del sistema, si tiene acceso a
 * ciertas paginas y recursos
 *
 * @author Marvin Moran Desarrollo
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/umg/*"})
//@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter, Serializable {

    private static final boolean debug = true;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    private String urlStr = "";
    private Compilacion config;

    public LoginFilter() {
        this.config = new Compilacion();
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("LoginFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         String values[] = request.getParameterValues(name);
         int n = values.length;
         StringBuffer buf = new StringBuffer();
         buf.append(name);
         buf.append("=");
         for(int i=0; i < n; i++) {
         buf.append(values[i]);
         if (i < n-1)
         buf.append(",");
         }
         log(buf.toString());
         System.out.println(buf.toString());
         }*/
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("LoginFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
	/*
         for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         Object value = request.getAttribute(name);
         log("attribute: " + name + "=" + value.toString());

         }
         */
        // For example, a filter might append something to the response.
	/*
         PrintWriter respOut = new PrintWriter(response.getWriter());
         respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Obtengo el bean que representa el usuario desde el scope sesión
        String loginBean = (String) req.getSession().getAttribute("usuario");

        //Proceso la URL que está requiriendo el cliente
        urlStr = req.getRequestURL().toString().toLowerCase();

        //Obtiene los Accesos del usuario
        List<UsuarioModel> sisAccesos = (List<UsuarioModel>) req.getSession().getAttribute("SisAccesos");

        //Crea el objeto de UusariosBean
        UsuariosBean userBean = new UsuariosBean();
        userBean.setTitulo(urlStr, sisAccesos);
        //System.out.println("url "+urlStr);
        boolean noProteger = noProteger(urlStr);

        //Si no requiere proteccion sigue normalmente.
        if (noProteger(urlStr)) {
            chain.doFilter(request, response);
            return;
        }

        //Proceso para proteger paginas si no tiene permiso*/
        boolean permiso = true;
        String pagNav = urlStr.substring(urlStr.lastIndexOf("/") + 1);

        if (sisAccesos != null) {
            for (UsuarioModel result : sisAccesos) {
                String pagMen = result.getNombrePrograma();
                pagMen = pagMen + ".xhtml";
                if (pagMen.matches(pagNav) || pagNav.matches("index.xhtml") || pagNav.matches("login.xhtml") || pagNav.matches("construccion.xhtml")) {
                    permiso = true;
                }
            }
        }
        
        config.getDirecCompile();

        if (!permiso) {
            res.sendRedirect(config.getDirecCompile() + "/Golosinas/umg/Login.xhtml");
            return;
        }

        //Si esta logueado o No
        if (loginBean == null || userBean.getEstaLogeado() == true) {
            res.sendRedirect(config.getDirecCompile() + "/Golosinas/umg/Login.xhtml");
            return;

        }
        chain.doFilter(request, response);

    }

    //paginas No protegidas
    private boolean noProteger(String urlStr) {
        if (urlStr.endsWith("login.xhtml")) {
            return true;
        }
        //Si esta en desarrollo quita proteccion
        if (config.getDesarrollo()) {
            if (urlStr.endsWith(config.getPaginaPrueba())) {
                return true;
            }
        }

        if (urlStr.indexOf("/javax.faces.resource/") != -1) {
            return true;
        }
        return false;
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("LoginFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("LoginFilter()");
        }
        StringBuffer sb = new StringBuffer("LoginFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

    public String getUrlStr() {
        return urlStr;
    }

    public void setUrlStr(String urlStr) {
        this.urlStr = urlStr;
    }

}
