package api.rest.Devsu.ejerciciopractico.service.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException(String mensaje) {
		super(mensaje);
	}

	public ServiceException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
