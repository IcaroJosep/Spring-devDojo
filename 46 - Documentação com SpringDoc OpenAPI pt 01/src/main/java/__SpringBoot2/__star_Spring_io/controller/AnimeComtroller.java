package __SpringBoot2.__star_Spring_io.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springdoc.core.annotations.ParameterObject;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import __SpringBoot2.__star_Spring_io.dominio.Anime;
import __SpringBoot2.__star_Spring_io.mapper.AnimeMapper;
import __SpringBoot2.__star_Spring_io.repository.AnimeRepository;
import __SpringBoot2.__star_Spring_io.requests.AnimePostRequestBody;
import __SpringBoot2.__star_Spring_io.services.AnimeServices;
import __SpringBoot2.__star_Spring_io.util.DateUtil;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController

@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
@Validated
public class AnimeComtroller {

	private final DateUtil dateUtil;
	private final AnimeServices animeServices;
	private final AnimeRepository animeRepository;

	@GetMapping // altteraçao em .proprieties na parte de queries SQL geradas pelo Hibernate
				// (final do arqquivo).
	/*
	   ABORDAGEM ANTERIOR (Manual):PARA EXIBIÇAO NO SWAGGER"NAO TEN ALTERAÇAO NO FUNCIONAMENTO DO SISTEMA"!!!!!!!!!!!!!!!!!
	   @PageableAsQueryParam -> Desenha os campos na UI.
	   @Parameter(hidden = true) -> Evita que o objeto bruto apareça duplicado.
	
	@PageableAsQueryParam	//Adiciona os campos amigáveis (page, size, sort) na interface do Swagger
	public ResponseEntity<Page<Anime>> list(@Parameter(hidden = true)//Esconde o objeto Pageable bruto para evitar duplicidade e poluição visual na doc
											Pageable pageable) {									
	*/	
	public ResponseEntity<Page<Anime>> list(@ParameterObject// ABORDAGEM MODERNA (Automática): PARA EXIBIÇAO NO SWAGGER"NAO TEN ALTERAÇAO NO FUNCIONAMENTO DO SISTEMA"!!!!!!
												            // 'Abre' o objeto e expõe seus campos como parâmetros de busca.
												            // É genérico: funciona para Pageable ou qualquer DTO de filtro.			
											Pageable pageable) {
		log.info(dateUtil.formatLocalDataTimeToDatabaseStyle(LocalDateTime.now()));
		Page<Anime> listAnime = animeServices.listAll(pageable);

		return ResponseEntity.ok(listAnime);
	}

	@GetMapping(path = "findByName")
	public ResponseEntity<Page<Anime>> findByName(
			@Parameter(hidden = true)
			Pageable pageable,
			@RequestParam @NotBlank(message = "Nome não pode ser vazio")
			@Parameter(description = "Nome de 1 a 50 caracteres")//add descriçao para o swagger
			@Size(min = 1, max = 50, message = "Nome deve ter entre 1 e 50 caracteres") 
			@Pattern(regexp = "^[a-zA-Z0-9áàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ\\s\\-._]*$", message = "Caracteres inválidos no nome") 
			String name,
			@RequestParam(defaultValue = "false") 
			boolean comtem
			) {

		Page<Anime> listAnime = animeServices.findByName(pageable, name, comtem);

		return ResponseEntity.ok(listAnime);
	}
	
	@GetMapping(path = "by-id/{id}")
	public ResponseEntity<Optional<Anime>> findByid(@PathVariable long id,
													//captura de altencicaçao recebida e atribuiçao a de valor
													@AuthenticationPrincipal UserDetails userDetails ){
			log.info(userDetails);
				
		return ResponseEntity.ok(animeRepository.findById(id));/*ATENÇAO ussi incoreto de repository para 
																funcionar de ve ingetado "private final AnimeRepository animeRepository;"
																que se encomtra no inicio da classe
		 														*/
	}

	@PostMapping("/adm") // assim como getmaping se ouver apenas um as requisicoes post serao mapeadas
					// automaticamentes para ele
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody animePostRequestBody,
										@AuthenticationPrincipal UserDetails userDetails) {
		
		log.info(userDetails);
		
		
		// return ResponseEntity.created(AnimeServices.save(anime));

		Anime animeSalvo = animeServices.save(animePostRequestBody);
		return new ResponseEntity<>(animeSalvo, HttpStatus.CREATED);
	}
	//a adiçao de /adm serve para a verifucaçao de altorizaçao centralisada contida em SecuretyConfig
	@DeleteMapping("/adm/{id}")
	public ResponseEntity<Anime> delete(@PathVariable Long id ){
		return ResponseEntity.ok(animeServices.deleteById(id));		
	}
	
	@PutMapping("/adm")
	public ResponseEntity<Anime> update(@RequestBody @Valid AnimePostRequestBody animePostRequestBody){
		return ResponseEntity.ok(animeServices.updateForName(animePostRequestBody));
	}

}
