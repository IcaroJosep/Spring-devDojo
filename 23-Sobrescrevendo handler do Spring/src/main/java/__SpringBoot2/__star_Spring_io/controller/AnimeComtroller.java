package __SpringBoot2.__star_Spring_io.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import __SpringBoot2.__star_Spring_io.dominio.Anime;
import __SpringBoot2.__star_Spring_io.requests.AnimePostRequestBody;
import __SpringBoot2.__star_Spring_io.services.AnimeServices;
import __SpringBoot2.__star_Spring_io.util.DateUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController

@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor


/*
 * A classe RestExceptionHandler estende ResponseEntityExceptionHandler,
 * que já fornece implementações padrão para o tratamento de várias exceções do Spring MVC.
 *
 * Aqui foram adicionados:
 * - Métodos @ExceptionHandler para tratar exceções específicas (como BedRequestException e MethodArgumentNotValidException).
 * - Sobrescrita do método handleExceptionInternal(), herdado da superclasse,
 *   permitindo personalizar a resposta para erros não tratados explicitamente,
 *   retornando um objeto ExceptionDetails padronizado.
 *
 * Dessa forma, a aplicação passa a ter mensagens de erro consistentes e centralizadas,
 * tanto para exceções personalizadas quanto para exceções genéricas.
 */


public class AnimeComtroller {
	
	private final DateUtil dateUtil; //sendo costruido por @RequiredArgsConstructor
	private final AnimeServices animeServices;
	
	/* @valid valoda os parametros obrigatorios colocados na classes TDO's AnimePostRequestBody e animePutRequestBody.
	 * */
	@PostMapping
	public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody animePostRequestBody){
			
		return new ResponseEntity<>(animeServices.save(animePostRequestBody),HttpStatus.CREATED);
	}
	
}

