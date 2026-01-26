package __SpringBoot2.__star_Spring_io.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import __SpringBoot2.__star_Spring_io.dominio.Anime;
import __SpringBoot2.__star_Spring_io.exception.BedRequestException;
import __SpringBoot2.__star_Spring_io.mapper.AnimeMapper;
import __SpringBoot2.__star_Spring_io.repository.AnimeRepository;
import __SpringBoot2.__star_Spring_io.requests.AnimePostRequestBody;
import __SpringBoot2.__star_Spring_io.seguranca.PageValid;
import __SpringBoot2.__star_Spring_io.seguranca.PageableValidation;
import __SpringBoot2.__star_Spring_io.seguranca.Sanatizador;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimeServices {

	private final AnimeRepository animeRepository;
	private final AnimeMapper animeMapper;

	public Page<Anime> listAll(Pageable pageable) {
		Pageable pageableRequest = PageableValidation.validateAndSanitize(pageable);

		Page<Anime> pSani = PageValid.ValidaSanitizaPageAnime(animeRepository.findAll(pageableRequest));

		return pSani;
	}

	public Page<Anime> findByName(Pageable pageable, String name, boolean comtem) {

		Pageable pageableRequest = PageableValidation.validateAndSanitize(pageable);
		Page<Anime> pSani;
		String nSani = Sanatizador.saniString(name);

		if (nSani == null) {
			throw new BedRequestException("nome invalido");
		}

		if (comtem) {
			pSani = PageValid.ValidaSanitizaPageAnime(animeRepository.findByNameContaining(nSani, pageableRequest));
			return pSani;
		}

		pSani = PageValid.ValidaSanitizaPageAnime(animeRepository.findByName(nSani, pageableRequest));

		return pSani;
	}

	public Anime save(AnimePostRequestBody animePostRequestBody) {

		AnimePostRequestBody dtoSanatizado = new AnimePostRequestBody();

		String nameSani = Sanatizador.saniString(animePostRequestBody.getName());

		if (nameSani != null && nameSani.trim().isEmpty()) {
			throw new BedRequestException("Digite um nome válido (apenas tags HTML não são permitidas)");
		}
		dtoSanatizado.setName(nameSani);

		Anime animeInp = animeMapper.toAnime(dtoSanatizado);
		Anime animeSalvo = animeRepository.save(animeInp);

		return Sanatizador.saniAnime(animeSalvo);
	}

	public Anime deleteById(Long id) {
		Optional<Anime> caixa = animeRepository.findById(id);
		Anime anime = caixa.orElseThrow(()->new BedRequestException("id nao encomtrado"));
		animeRepository.delete(anime);
		return anime;
	}

		@Transactional
	public Anime updateForName(@Valid AnimePostRequestBody animePostRequestBody) {
			Optional<Anime> caixa = animeRepository.findById(animePostRequestBody.getId());
			Anime anime = caixa.orElseThrow(()->new BedRequestException("id nao encomtrado"));
	    	anime.setName(animePostRequestBody.getName());		
			
			return anime;
	}
}
