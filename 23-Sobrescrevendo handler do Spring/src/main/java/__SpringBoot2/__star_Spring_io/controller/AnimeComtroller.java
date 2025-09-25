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
		/*compreençao complera sobre valid e a extençao de hendler */
		/*obs: deixei com a opçao de reescrita @overwrid*/

/*🔹 Cenário 1 — Usando @Valid (fluxo padrão do Spring)

Você anota o parâmetro do controller com @Valid.

Se a validação falhar (@NotEmpty, @NotNull, etc.), o Spring automaticamente lança MethodArgumentNotValidException.

Como você estende ResponseEntityExceptionHandler, cai no método herdado handleMethodArgumentNotValid(...).

Se você quiser personalizar, precisa sobrescrever esse método.

➡️ Vantagem: menos código, segue o fluxo oficial.
➡️ Desvantagem: você fica preso a sobrescrever métodos da superclasse (não dá pra usar @ExceptionHandler simples nesse caso).

🔹 Cenário 2 — Ignorando a herança e usando suas próprias Exceptions

Você não depende do @Valid automático.

Você mesmo valida os campos (ex.: manualmente no serviço ou controller) e lança sua própria exception (ex.: CustomValidationException).

Aí você trata essa exception com @ExceptionHandler(CustomValidationException.class) e devolve exatamente o corpo que quiser.

➡️ Vantagem: você tem controle total e usa seu handler sem ambiguidade.
➡️ Desvantagem: perde a automação do @Valid (precisa escrever validações manuais ou utilitários).

🔹 Cenário 3 — Usando @Valid mas sem herdar de ResponseEntityExceptionHandler

Você remove o extends ResponseEntityExceptionHandler.

Cria um @ControllerAdvice apenas com métodos @ExceptionHandler.

Quando @Valid falhar, ainda será lançado MethodArgumentNotValidException.

Como não há ambiguidade com a superclasse, o Spring chamará o seu método @ExceptionHandler(MethodArgumentNotValidException.class) normalmente.

➡️ Esse é um meio termo: mantém a validação automática do Spring, mas ainda usa só seus próprios métodos de tratamento.

✅ Conclusão

Sua leitura está certa:

Se você usa @Valid + extends ResponseEntityExceptionHandler, precisa sobrescrever os métodos herdados.

Se não quiser isso, tem duas opções:

não herdar da superclasse e usar apenas @ExceptionHandler;

não usar @Valid e lançar suas próprias exceptions personalizadas.*/

