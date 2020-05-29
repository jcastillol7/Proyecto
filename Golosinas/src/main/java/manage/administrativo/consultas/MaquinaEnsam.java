/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage.administrativo.consultas ;

import clases.Const;
import clases.WebServices;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Init;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import wrapper.FichaComercioModel;

import wrapper.ListadoEstablecimientoModel;
import wrapper.ListadoFichaComercioModel;
import wrapper.ListadoMaquinaEnsamModel;
import wrapper.ListadoMaquinasEstadoModel;
import wrapper.ListadoTiendaVent;
import wrapper.MaquinaEnsamModel;
import wrapper.MaquinaEstadoModel;
import wrapper.TiendaVentModel;
import wrapper.TokenModel;
import wrapper.establecimientoModel;

/**
 *
 * @author Josue
 */
@Named
@ViewScoped
public class MaquinaEnsam implements Serializable {

    private boolean estado;
    private ListadoMaquinaEnsamModel arrayDeJson;
    private TokenModel token;
    private List<MaquinaEnsamModel> listadoEstablecimientos;
    private List<MaquinaEnsamModel> filterEstablecimientos;
    
    @PostConstruct
    public void init(){
    consumidor();
    
    }
    

    public void consumidor() {
        try {
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
            //obtenerUsuarios
            parametros = new String[0][0];
           
            arrayDeJson = new ListadoMaquinaEnsamModel();
            arrayDeJson = (ListadoMaquinaEnsamModel) services.consumidorDerecursoPorTokenGet(parametros, "http://"+Const.rutaServ+":8080/Ws-oauth2-gs/resources/reportes/reporteMaquinaEnsam",
                    "Bearer " + token.getAccessToken(), arrayDeJson);
            listadoEstablecimientos = arrayDeJson.getReporteMaquinasEnsam();
            if (token != null) {
                if (listadoEstablecimientos != null) {
                    estado = true;
                    System.out.println("Pase");
                    if (listadoEstablecimientos.size() > 0) {
                        estado = true;
                    } else {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No hay registros para los datos proporcionados.", "");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    }
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No hay registros para los datos proporcionados.", "");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            } else {
                System.out.println("Pase2");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario desconocido .", "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error conexion .", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        estado = true;
    }





public StreamedContent onImprime() throws JRException, IOException {
        String aplicacion = "";
        String nomArchivo = "";
        aplicacion = "application/pdf";
        nomArchivo = "MaquinasEnsambladas.pdf";   
        HashMap<String, Object> parameters = new HashMap<String, Object>(); 
        parameters.put("logo", "https://umg.edu.gt/50/img/logo-color.png");
        InputStream inputStream = null;
        ByteArrayOutputStream Teste = new ByteArrayOutputStream();
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listadoEstablecimientos);
        InputStream jasperReport = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/reports/rpt_maquinas_ensam.jasper");
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
        JRPdfExporter exporter = new net.sf.jasperreports.engine.export.JRPdfExporter();
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, Teste);
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        exporter.exportReport();
        inputStream = new ByteArrayInputStream(Teste.toByteArray());
        StreamedContent file = new DefaultStreamedContent(inputStream, aplicacion, nomArchivo);
        System.out.println("Clase"+ file.getClass());
        
        return file;
    }
    
    
    public void refresh() {
        estado = false;
        
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public ListadoMaquinaEnsamModel getArrayDeJson() {
        return arrayDeJson;
    }

    public void setArrayDeJson(ListadoMaquinaEnsamModel arrayDeJson) {
        this.arrayDeJson = arrayDeJson;
    }

    public List<MaquinaEnsamModel> getListadoEstablecimientos() {
        return listadoEstablecimientos;
    }

    public void setListadoEstablecimientos(List<MaquinaEnsamModel> listadoEstablecimientos) {
        this.listadoEstablecimientos = listadoEstablecimientos;
    }

    public List<MaquinaEnsamModel> getFilterEstablecimientos() {
        return filterEstablecimientos;
    }

    public void setFilterEstablecimientos(List<MaquinaEnsamModel> filterEstablecimientos) {
        this.filterEstablecimientos = filterEstablecimientos;
    }

   

    

    public TokenModel getToken() {
        return token;
    }

    public void setToken(TokenModel token) {
        this.token = token;
    }

   

}
