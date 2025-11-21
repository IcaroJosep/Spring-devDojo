package __SpringBoot2.__star_Spring_io.controller;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import __SpringBoot2.__star_Spring_io.dominio.Anime;
import __SpringBoot2.__star_Spring_io.services.AnimeServices;
import __SpringBoot2.__star_Spring_io.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController

@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor

public class AnimeComtroller {
	
	private final DateUtil dateUtil; 
	private final AnimeServices animeServices;
	
	
	@GetMapping					//altteraçao em .proprieties na parte de queries SQL geradas pelo Hibernate (final do arqquivo).
	public ResponseEntity<Page<Anime>> list(Pageable pageable){
		log.info(dateUtil.formatLocalDataTimeToDatabaseStyle(LocalDateTime.now()));
		return ResponseEntity.ok(animeServices.listAll(pageable));
	}
	
}

