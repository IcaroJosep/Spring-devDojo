package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BedRequestException extends RuntimeException {
	public BedRequestException (String messagen) {
		super(messagen);
	}
}
