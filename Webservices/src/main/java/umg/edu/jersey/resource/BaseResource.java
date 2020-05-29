package umg.edu.jersey.resource;

import javax.ws.rs.core.SecurityContext;

import org.springframework.security.oauth2.provider.OAuth2Authentication;

import umg.edu.jersey.exception.impl.AuthorizationException;

public class BaseResource {

    protected String ensureUserIsAuthorized(SecurityContext securityContext) {
        String user = (String) loadUserFromSecurityContext(securityContext);
        if (user != null) {
            return user;
        }
        throw new AuthorizationException("User not permitted to access this resource");
    }

    protected String loadUserFromSecurityContext(SecurityContext securityContext) {
        OAuth2Authentication requestingUser = (OAuth2Authentication) securityContext.getUserPrincipal();
        Object principal = requestingUser.getUserAuthentication().getPrincipal();
        String user = null;
        if (principal instanceof String) {
            user = (String) principal;
        }
        return user;
    }
 
}
