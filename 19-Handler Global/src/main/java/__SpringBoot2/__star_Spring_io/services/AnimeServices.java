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
import __SpringBoot2.__star_Spring_io.exception.BedRequestException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimeServices {
	
	private final AnimeRepository animeRepository;

	
	
	public Anime findByIdOrThrowBedRequestExeption(Long id) {		
		return animeRepository.findById(id)
				.orElseThrow(()->new BedRequestException("id anime not foud"));		
	}

/*	public Anime findByIdOrThrowBedRequestExeption(Long id) {
			
		return animeRepository.findById(id)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"id anime not foud"));		
	}


*/	
	

}
