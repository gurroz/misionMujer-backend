package au.com.rmit.misionMujer.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ElementAlreadyExistsException extends Exception {
}
