package __SpringBoot2.__star_Spring_io.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import __SpringBoot2.__star_Spring_io.exception.BedRequestException;
import __SpringBoot2.__star_Spring_io.exception.BedRequestExceptionDetails;
import __SpringBoot2.__star_Spring_io.exception.ExceptionDetails;
import __SpringBoot2.__star_Spring_io.exception.ValidationException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice //Diz ao Spring que essa classe vai gerenciar/tratar erros de forma global.
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	// Captura sua exceção customizada
	@ExceptionHandler(BedRequestException.class)//toda ves e for lançado o erro BedRequestException ele retornara este metodo 
	public ResponseEntity<BedRequestExceptionDetails> handlerBedResponseException(BedRequestException bre){
		return new ResponseEntity<>(
				BedRequestExceptionDetails.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.title("bed request exception , check the documentation")
				.details(bre.getMessage())
				.developerMessage(bre.getClass().getName())
				.build(),HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
	        MethodArgumentNotValidException exception,
	        HttpHeaders headers,
	        HttpStatusCode status,
	        WebRequest request){
		
	/*@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationException> handlMethodArgumentNotValidException(
	MethodArgumentNotValidException exception){
*/
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
		String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
			
		return new ResponseEntity<>(
				ValidationException.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.title("bed request exception , invalid fields")
				.details(exception.getMessage())
				.developerMessage(exception.getClass().getName())
				.fields(fields)
				.fieldsMessage(fieldsMessage)
				.build(),HttpStatus.BAD_REQUEST);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
			
			ExceptionDetails exceptionDetails = ExceptionDetails.builder().
					timestamp(LocalDateTime.now())
					.status(statusCode.value())
					.title(ex.getCause()!=null?ex.getCause().getMessage():"erro inesperado")
					.details(ex.getMessage())
					.developerMessage(ex.getClass().getName())
					.build();
			
		
		return new ResponseEntity<>(exceptionDetails,headers,statusCode);
	}
	
}
