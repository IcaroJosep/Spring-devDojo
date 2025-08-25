package __SpringBoot2.__star_Spring_io.controller;

import java.time.LocalDateTime;
import java.util.List;
import __SpringBoot2.__star_Spring_io.dominio.Anime;
import __SpringBoot2.__star_Spring_io.services.AnimeServices;
import __SpringBoot2.__star_Spring_io.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeComtroller {
	

	private final DateUtil dateUtil; //sendo costruido por @RequiredArgsConstructor
	private final AnimeServices animeServices;
	
	@GetMapping
	public List<Anime> list(){
		
		log.info(dateUtil.formatLocalDataTimeToDatabaseStyle(LocalDateTime.now()));
		
		return animeServices.listAll();
	}

}
