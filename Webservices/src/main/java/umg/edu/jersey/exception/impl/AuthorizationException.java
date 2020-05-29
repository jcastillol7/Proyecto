package umg.edu.jersey.exception.impl;

import umg.edu.jersey.exception.BaseWebApplicationException;

/**
 * Clase que se implementa la excepcion cuando se producce un error en la autenticacion
 * @author Chabir Atrahouch
 *
 */
public class AuthorizationException extends BaseWebApplicationException {

	private static final long serialVersionUID = 1L;

	public AuthorizationException(String applicationMessage) {
		super(403, "Not authorized", applicationMessage);
	}
}
