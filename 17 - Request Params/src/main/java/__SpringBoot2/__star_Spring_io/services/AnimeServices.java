package __SpringBoot2.__star_Spring_io.services;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import __SpringBoot2.__star_Spring_io.dominio.Anime;
import __SpringBoot2.__star_Spring_io.mapper.AnimeMapper;
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
		return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
	}

	public void delet(Long id) {
		animeRepository.delete(findByIdOrThrowBedRequestExeption(id));
	}


	public void atualisacao(AnimePutRequestBody animePutRequestBody) {
		Anime animeSaved =findByIdOrThrowBedRequestExeption(animePutRequestBody.getId());
		Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
		anime.setId(animeSaved.getId());
		
		animeRepository.save(anime);
	}

	public List<Anime> findByName(String name) {
		return  animeRepository.findByName(name);
	}
	public List<Anime> findByNameContaining(String name) {
		return  animeRepository.findByNameContaining(name);
	}
	

}
