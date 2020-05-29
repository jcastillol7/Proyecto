package umg.edu.jersey.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JerseyCrossFilter extends BaseCORSFilter implements ContainerResponseFilter {

	@Value("${cors.allowed.origins}")
	String allowedOriginsString;

	@Override
	public void filter(ContainerRequestContext requestContext,ContainerResponseContext responseContext) throws IOException {
		
		if (requestContext.getHeaders().containsKey("Origin")) 
		{
			String origin = requestContext.getHeaders().getFirst("Origin");
			if (getAllowedOrigins(allowedOriginsString).contains(origin)) 
			{					
				responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
				responseContext.getHeaders().add("Access-Control-Allow-Methods","GET, POST, DELETE, OPTIONS, PUT");
				responseContext.getHeaders().add("Access-Control-Allow-Headers","X-CSRF-Token, X-Requested-With, Accept, Accept-Version, Content-Length, Content-MD5, Content-Type, Date, X-Api-Version,X-HTTP-Method-Override, Origin, Authorization");
				responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
				responseContext.getHeaders().add("Access-Control-Expose-Headers", "Location");
				responseContext.getHeaders().add("Access-Control-Max-Age","3600");
			}
		}
		
	}
}