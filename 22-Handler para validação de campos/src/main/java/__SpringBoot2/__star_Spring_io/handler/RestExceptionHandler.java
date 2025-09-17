package __SpringBoot2.__star_Spring_io.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import __SpringBoot2.__star_Spring_io.exception.BedRequestException;
import __SpringBoot2.__star_Spring_io.exception.BedRequestExceptionDetails;
import __SpringBoot2.__star_Spring_io.exception.ValidationException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice //Diz ao Spring que essa classe vai gerenciar/tratar erros de forma global.
public class RestExceptionHandler {
	
	// Captura sua exceção customizada
	@ExceptionHandler(BedRequestException.class)//toda ves e for lançado o erro BedRequestException ele retornara este metodo 
	public ResponseEntity<BedRequestExceptionDetails> handlerBedResponseException(BedRequestException bre){
		return new ResponseEntity<>(
				BedRequestExceptionDetails.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.tittle("bed request exception , check the documentation")
				.details(bre.getMessage())
				.developerMessage(bre.getClass().getName())
				.build(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)//toda ves e for lançado o erro MethodArgumentNotValidException ele retornara este metodo 
	public ResponseEntity<ValidationException> handlMethodArgumentNotValidException(MethodArgumentNotValidException exception){

		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
		String fieldsMassage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
			
		return new ResponseEntity<>(
				ValidationException.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.tittle("bed request exception , invalid fields")
				.details(exception.getMessage())
				.developerMessage(exception.getClass().getName())
				.fields(fields)
				.fieldsMessage(fieldsMassage)
				.build(),HttpStatus.BAD_REQUEST);
	}
	
}
