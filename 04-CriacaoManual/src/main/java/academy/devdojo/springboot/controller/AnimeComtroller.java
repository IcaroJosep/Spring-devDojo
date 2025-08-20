package academy.devdojo.springboot.controller;

import java.util.List;
import academy.devdojo.springboot.dominio.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController/*Marca a classe como controller REST (equivale a @Controller + @ResponseBody).
				➜ O retorno dos métodos é serializado (normalmente em JSON) automaticamente.*/

@RequestMapping("anime")/*Define o prefixo de rota para a classe.
						➜ Todas as rotas aqui começam com /anime (com ou sem / funciona; por clareza costuma-se usar "/anime").*/
public class AnimeComtroller {
	
	@GetMapping(path="list")/*Mapeia uma rota GET com sufixo list.
							➜ Junto com o @RequestMapping("anime"), a URL final é /anime/list (recomendo @GetMapping("/list") para ficar explícito).*/
	public List<Anime> list(){
		return List.of(new Anime("DGB"),new Anime("Bersek"));
	}

}
