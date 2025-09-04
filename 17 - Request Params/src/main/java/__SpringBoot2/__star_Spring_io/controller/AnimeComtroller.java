package __SpringBoot2.__star_Spring_io.controller;

import java.time.LocalDateTime;
import java.util.List;
import __SpringBoot2.__star_Spring_io.dominio.Anime;
import __SpringBoot2.__star_Spring_io.requests.AnimePostRequestBody;
import __SpringBoot2.__star_Spring_io.requests.AnimePutRequestBody;
import __SpringBoot2.__star_Spring_io.services.AnimeServices;
import __SpringBoot2.__star_Spring_io.util.DateUtil;
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

@RestController

@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeComtroller {
	

	private final DateUtil dateUtil; //sendo costruido por @RequiredArgsConstructor
	private final AnimeServices animeServices;
	
	@GetMapping
	public ResponseEntity<List<Anime>> list(){
		log.info(dateUtil.formatLocalDataTimeToDatabaseStyle(LocalDateTime.now()));
		return new ResponseEntity<>(animeServices.listAll(),HttpStatus.OK);
	}

	@GetMapping(path = 	"/{id}")
	public ResponseEntity<Anime> findById(@PathVariable Long id){
		return new ResponseEntity<>(animeServices.findByIdOrThrowBedRequestExeption(id),HttpStatus.OK);
	}
	
	@PostMapping //assim como getmaping se ouver apenas um as requisicoes post serao mapeadas automaticamentes para ele
	public ResponseEntity<Anime> save(@RequestBody AnimePostRequestBody animePostRequestBody){
		//return ResponseEntity.created(AnimeServices.save(anime));		
		return new ResponseEntity<>(animeServices.save(animePostRequestBody),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delet(@PathVariable Long id){
		animeServices.delet(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping
	public ResponseEntity<Void> atualisacao(@RequestBody AnimePutRequestBody animePutRequestBody){
		animeServices.atualisacao(animePutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = 	"/find")/*/http:/localhost:8080/animes/find?name=bersek
								  /http:/localhost:8080/animes/find?name=tokyo&comtem=true*/
	public ResponseEntity<List<Anime>> findByName( @RequestParam String name,
	        @RequestParam( defaultValue = "false" ) Boolean comtem){
			return new ResponseEntity<>(animeServices.findByName(name,comtem),HttpStatus.OK);
		
	} 






}

