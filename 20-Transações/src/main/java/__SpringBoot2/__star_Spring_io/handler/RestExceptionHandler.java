package __SpringBoot2.__star_Spring_io.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import __SpringBoot2.__star_Spring_io.exception.BedRequestException;
import __SpringBoot2.__star_Spring_io.exception.BedRequestExceptionDetails;

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
	
}
