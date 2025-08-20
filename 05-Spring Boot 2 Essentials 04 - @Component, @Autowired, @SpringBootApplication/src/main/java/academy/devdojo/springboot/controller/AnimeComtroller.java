package academy.devdojo.springboot.controller;

import java.time.LocalDateTime;
import java.util.List;
import academy.devdojo.springboot.dominio.*;
import academy.devdojo.springboot.util.DateUtil;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("anime")
@Log4j2
public class AnimeComtroller {
	
//	@Autowired : é indicado ussar o costrutor  ou o lombok para inicializaçao da variavel
	private DateUtil dateUtil;
	public AnimeComtroller(DateUtil dateUtil) {
		this.dateUtil=dateUtil;
	}
	
	@GetMapping(path="list")
	public List<Anime> list(){
		log.info(dateUtil.formatLocalDataTimeToDatabaseStyle(LocalDateTime.now()));
		return List.of(new Anime("DGB"),new Anime("Bersek"),new Anime("black clover"));
	}

}
