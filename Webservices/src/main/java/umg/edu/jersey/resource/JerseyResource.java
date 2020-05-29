package umg.edu.jersey.resource;

import java.util.Date;
import java.util.Locale;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("jersey-hello")
@Component
@Scope(value="request")
/**
 * Clase que representa nuestra Api Rest
 * @author Chabir Atrahouch
 *
 */
public class JerseyResource extends BaseResource {
   // private static final Logger LOGGER = LoggerFactory.getLogger(JerseyResource.class);
    private static final Locale LOCALE_SP = new Locale("ES");
    

    public JerseyResource() {
        //LOGGER.info("HelloWorldResource()");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHello(@Context HttpHeaders headers,@Context SecurityContext securityContext) {
    	String result = "Upps!!";
    	Locale localeHeader;
    	String token = ensureUserIsAuthorized(securityContext);
    	if(headers.getAcceptableLanguages().size() > 0)
    	{
    		localeHeader = headers.getAcceptableLanguages().get(0);
    		if(Locale.ENGLISH.equals(localeHeader))
	    	{
	    		result = String.format("%s: %s %s", new Date(), "Hello world", token);
	    	}
	    	else if(LOCALE_SP.equals(localeHeader))
	    	{
	    		result = String.format("%s: %s %s", new Date(), "Hola mundo ", token);
	    	}
    	}
        //LOGGER.info(result);
        return result;
    }

}
