package academy.devdojo.springboot.controller;

import java.util.List;
import academy.devdojo.springboot.dominio.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("anime")
public class AnimeComtroller {
	
	@GetMapping(path="list")
	public List<Anime> list(){
		
		return List.of(new Anime("DGB"),new Anime("Bersek"));
	}

}
