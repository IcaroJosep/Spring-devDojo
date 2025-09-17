package __SpringBoot2.__star_Spring_io.exception;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ExceptionDetails {
	protected int status;
	protected String details;
	protected String tittle;
	protected String developerMessage;
	protected LocalDateTime timestamp;
}
