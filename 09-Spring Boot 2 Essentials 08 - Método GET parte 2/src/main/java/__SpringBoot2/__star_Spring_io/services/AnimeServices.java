package __SpringBoot2.__star_Spring_io.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import __SpringBoot2.__star_Spring_io.dominio.Anime;

@Service
public class AnimeServices {
	List<Anime> animes= List.of(new Anime(12l, "DGB"),new Anime(12l, "bersek"));
	
	public List<Anime> listAll() {
		return  animes;
	}
	
	public Anime findById(Long id) {
		return  animes.stream()
				.filter(anime-> id.equals(anime.getId()))
				.findFirst()
				.orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"id anime not foud"));
		
	}

}
