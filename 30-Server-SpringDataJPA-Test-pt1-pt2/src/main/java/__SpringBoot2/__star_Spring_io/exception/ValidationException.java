package __SpringBoot2.__star_Spring_io.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationException extends ExceptionDetails {
	private final String fields;
	private final String fieldsMessage;
	
}
