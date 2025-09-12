package __SpringBoot2.__star_Spring_io.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BedRequestExceptionDetails {
	private String tittle;
	private int status;
	private String details;
	private String developerMessage;
	private LocalDateTime timestamp;
}
