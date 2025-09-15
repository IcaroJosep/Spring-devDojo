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
import jakarta.transaction.Transactional;
import __SpringBoot2.__star_Spring_io.exception.BedRequestException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimeServices {
	
	private final AnimeRepository animeRepository;

	
	@Transactional(rollbackOn = Exception.class)
	public  Anime save(AnimePostRequestBody animePostRequestBody){
		
		if(animeRepository.findByName(animePostRequestBody.getName()).size()>1) {
			throw new BedRequestException("ja existente");
		}

		Anime anime = animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
	
		return anime;
		
	}
	

}
