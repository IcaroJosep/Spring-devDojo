package __SpringBoot2.__star_Spring_io.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import __SpringBoot2.__star_Spring_io.dominio.Anime;
import __SpringBoot2.__star_Spring_io.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimeServices {
	
	private final AnimeRepository animeRepository;
	
		
	public Page<Anime> listAll(Pageable pageable/*int page ,int size ,String sortby*/) {
		/*Pageable pageable=PageRequest.of(page,size,Sort.by(sortby).ascending());*/
		return  animeRepository.findAll(pageable);
	}
	
	
	

}
