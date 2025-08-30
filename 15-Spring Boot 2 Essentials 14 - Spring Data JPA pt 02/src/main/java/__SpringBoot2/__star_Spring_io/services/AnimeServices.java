package __SpringBoot2.__star_Spring_io.services;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import __SpringBoot2.__star_Spring_io.dominio.Anime;
import __SpringBoot2.__star_Spring_io.repository.AnimeRepository;
import __SpringBoot2.__star_Spring_io.requests.AnimePostRequestBody;
import __SpringBoot2.__star_Spring_io.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimeServices {
	
	private final AnimeRepository animeRepository;

	public List<Anime> listAll() {
		return  animeRepository.findAll();
	}
	
	public Anime findByIdOrThrowBedRequestExeption(Long id) {
			
		return animeRepository.findById(id)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"id anime not foud"));		
	}

	public  Anime save(AnimePostRequestBody animePostRequestBody) {
		
		Anime anime = Anime.builder()
				.name(animePostRequestBody.getName())
				.build();
		return animeRepository.save(anime);

	}

	public void delet(Long id) {
		animeRepository.delete(findByIdOrThrowBedRequestExeption(id));
	}


	public void atualisacao(AnimePutRequestBody animePutRequestBody) {
		Anime animeSaved =findByIdOrThrowBedRequestExeption(animePutRequestBody.getId());
		
		Anime anime = Anime.builder()
				.id(animeSaved.getId())
				.name(animePutRequestBody.getName())
				.build();
		
		animeRepository.save(anime);
	}

}
