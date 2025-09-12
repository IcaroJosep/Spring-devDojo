package __SpringBoot2.__star_Spring_io.controller;

import java.time.LocalDateTime;
import java.util.List;
import __SpringBoot2.__star_Spring_io.dominio.Anime;
import __SpringBoot2.__star_Spring_io.requests.AnimePostRequestBody;
import __SpringBoot2.__star_Spring_io.requests.AnimePutRequestBody;
import __SpringBoot2.__star_Spring_io.services.AnimeServices;
import __SpringBoot2.__star_Spring_io.util.DateUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController

@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeComtroller {
	
	/*inplementaçao feita na classe anime services com utilisaçao de bedrequestExeption
	 * 
	 * salva anime depos testa caso aja mas de um da rollback e tirando o anime salvo
	 * 
	 * :problema ele como ele salvou ele pula para o proximo id!
	 * 
	 * */
	

	private final DateUtil dateUtil; //sendo costruido por @RequiredArgsConstructor
	private final AnimeServices animeServices;
	

	@PostMapping //assim como getmaping se ouver apenas um as requisicoes post serao mapeadas automaticamentes para ele
	public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody animePostRequestBody){
		//return ResponseEntity.created(AnimeServices.save(anime));		
		return new ResponseEntity<>(animeServices.save(animePostRequestBody),HttpStatus.CREATED);
	}
	
}

