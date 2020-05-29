package umg.edu.jersey;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import umg.edu.jersey.exception.GenericExceptionMapper;
import umg.edu.jersey.filter.JerseyCrossFilter;

/**
 * Clase que se encarga de configurar los servicios Rest
 * @author Chabir Atrahouch
 *
 */
public class MyApplication extends ResourceConfig {
	
	private Map<String, MediaType> mediaTypeMap;
	private Map<String, String> languageTypeMap;
	
    public MyApplication () {
    	packages("umg.edu.jersey.resource", "umg.edu.jersey.exception");
    	register(RequestContextFilter.class);
        register(JacksonFeature.class);
        register(GenericExceptionMapper.class);
        //filter CORS
        ApplicationContext rootCtx = ContextLoader.getCurrentWebApplicationContext();
        ContainerResponseFilter filter = rootCtx.getBean(JerseyCrossFilter.class);
        register(filter);
        
        if (mediaTypeMap == null)
        {
            mediaTypeMap = new HashMap<String, MediaType>();
            mediaTypeMap.put("json", MediaType.APPLICATION_JSON_TYPE);
            mediaTypeMap.put("xml", MediaType.APPLICATION_XML_TYPE);
            mediaTypeMap.put("txt", MediaType.TEXT_PLAIN_TYPE);
            mediaTypeMap.put("html", MediaType.TEXT_HTML_TYPE);
            mediaTypeMap.put("xhtml", MediaType.APPLICATION_XHTML_XML_TYPE);
            MediaType jpeg = new MediaType("image", "jpeg");
            mediaTypeMap.put("jpg", jpeg);
            mediaTypeMap.put("jpeg", jpeg);
            mediaTypeMap.put("zip", new MediaType("application", "x-zip-compressed"));
        }
        property(ServerProperties.MEDIA_TYPE_MAPPINGS, mediaTypeMap);
        
        
        if(languageTypeMap == null)
        {
        	languageTypeMap = new HashMap<String, String>();
        	languageTypeMap.put("english", "en");
        	languageTypeMap.put("spanish", "es");
        }
        
        property(ServerProperties.LANGUAGE_MAPPINGS, languageTypeMap);
    }
    
}
