package __SpringBoot2.__star_Spring_io.controller;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import __SpringBoot2.__star_Spring_io.requests.AnimePostRequestBody;
import __SpringBoot2.__star_Spring_io.services.AnimeServices;
import __SpringBoot2.__star_Spring_io.util.DateUtil;
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

	@GetMapping // altteraçao em .proprieties na parte de queries SQL geradas pelo Hibernate
				// (final do arqquivo).
	public ResponseEntity<Page<Anime>> list(Pageable pageable) {

		log.info(dateUtil.formatLocalDataTimeToDatabaseStyle(LocalDateTime.now()));
		Page<Anime> listAnime = animeServices.listAll(pageable);

		return ResponseEntity.ok(listAnime);
	}

	@GetMapping(path = "findByName")
	public ResponseEntity<Page<Anime>> list(
			Pageable pageable,
			@RequestParam @NotBlank(message = "Nome não pode ser vazio")
			@Size(min = 1, max = 50, message = "Nome deve ter entre 1 e 50 caracteres") 
			@Pattern(regexp = "^[a-zA-Z0-9áàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ\\s\\-._]*$", message = "Caracteres inválidos no nome") 
			String name,
			@RequestParam(defaultValue = "false") 
			boolean comtem
			) {

		Page<Anime> listAnime = animeServices.findByName(pageable, name, comtem);

		return ResponseEntity.ok(listAnime);
	}

	@PostMapping // assim como getmaping se ouver apenas um as requisicoes post serao mapeadas
					// automaticamentes para ele
	public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody animePostRequestBody) {

		// return ResponseEntity.created(AnimeServices.save(anime));

		Anime animeSalvo = animeServices.save(animePostRequestBody);
		return new ResponseEntity<>(animeSalvo, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Anime> delete(@PathVariable Long id ){
		return ResponseEntity.ok(animeServices.deleteById(id));		
	}
	
	@PutMapping
	public ResponseEntity<Anime> update(@RequestBody @Valid AnimePostRequestBody animePostRequestBody){
		return ResponseEntity.ok(animeServices.updateForName(animePostRequestBody));
	}

}
