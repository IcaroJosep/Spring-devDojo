package __SpringBoot2.__star_Spring_io.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;

import __SpringBoot2.__star_Spring_io.dominio.Anime;
import __SpringBoot2.__star_Spring_io.util.AnimeCreator;
import jakarta.persistence.Column;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;

//a validação do Jakarta Validation é desativada :
@DataJpaTest(properties = {
        "spring.jpa.properties.jakarta.persistence.validation.mode=none"
    })

@DisplayName("testes para o repositorio animes")
@Log4j2

class AnimeRepositoryTest {
	
	@Autowired
	private AnimeRepository animeRepository;
	
 // trocado por usso da classe util de AnimeCreate
 /*	private Anime createAnime() {
		return Anime.builder().name("zica full").build();
	}
*/	
	@Test
	@DisplayName("Save Persist anime when Successfull")
	void save_PersistAnime_WhenSuccessful() {
		Anime animeToBeSaved  = AnimeCreator.createAnimeToBeSaved();
		Anime animeSaved = this.animeRepository.save(animeToBeSaved);
		
		log.info("To Be Saved: {}, Saved: {}", animeToBeSaved, animeSaved);
		
		Assertions.assertThat(animeSaved).isNotNull();
		Assertions.assertThat(animeSaved.getId()).isNotNull();
		Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());
	}
	
		
	@Test
	@DisplayName("Save update anime when Successfull")
	void save_UpdateAnime_WhenSuccessful() {
		Anime animeToBeSaved  = AnimeCreator.createAnimeToBeSaved();	
		Anime animeSaved = this.animeRepository.save(animeToBeSaved);
		
		animeSaved.setName("kakashi");
		Anime animeUpdated = this.animeRepository.save(animeSaved);
		
		log.info("Updated: {}, Saved: {}", animeUpdated, animeSaved);
		
		Assertions.assertThat(animeUpdated).isNotNull();
		Assertions.assertThat(animeUpdated.getId()).isNotNull();
		Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeSaved.getName());
		Assertions.assertThat(animeUpdated.getId()).isEqualTo(animeSaved.getId());

	}
	
	@Test
	@DisplayName("Delete Remove anime when Successfull")
	void delete_RemovesAnime_WhenSuccessful() {
		Anime animeToBeSaved  = AnimeCreator.createAnimeToBeSaved();	
		
		Anime animeSaved = this.animeRepository.save(animeToBeSaved);
		
		
		
		this.animeRepository.delete(animeSaved);
		this.animeRepository.flush();
		
		
		Optional<Anime> animeOptional = this.animeRepository.findById(animeSaved.getId());
		
		Assertions.assertThat(animeOptional).isEmpty();

	}
	
	@Test
	@DisplayName("Find By Name Returns list of anime when Successfull")
	void findByName_ReturneListOfAnime_WhenSuccessful() {
		Anime animeToBeSaved  = AnimeCreator.createAnimeToBeSaved();	
		
		Anime animeSaved = this.animeRepository.save(animeToBeSaved);
		
		String name = animeSaved.getName();
		
		List<Anime> animes = this.animeRepository.findByName(name,PageRequest.of(0, 5)).getContent();
		
		log.info("anime salvo : {} , name pesquisado : {} , anime listado: {}.",animeSaved,name,animes);
		
		Assertions.assertThat(animes)
					.isNotEmpty()
					.contains(animeSaved);

	}	
	
	@Test
	@DisplayName("Find By Name Returns Enpty list when no anime is found")
	void findByName_ReturneEnptyList_WhenAnimeIsNotFound() {
			
		
		List<Anime> animes = this.animeRepository.findByName("xuxa",PageRequest.of(0, 5)).getContent();
		
		Assertions.assertThat(animes).isEmpty();
		Assertions.assertThat(animes).isNotNull();
	}
	
	
	/*
	 * Este teste valida a restrição definida pela anotação:
	 * @Column(nullable = false, length = 100)
	 *
	 * Essa anotação não pertence à validação do Bean Validation (Jakarta Validation),
	 * mas sim à definição da estrutura da coluna no banco de dados. Ou seja, é o próprio
	 * banco que impede que um valor null seja persistido.
	 *
	 * Por esse motivo, a validação do Jakarta Validation é desativada neste teste através de:
	 *
	 * @DataJpaTest(properties = {
	 *   "spring.jpa.properties.jakarta.persistence.validation.mode=none"
	 * })
	 *
	 * Assim, anotações como @NotBlank e @NotEmpty são ignoradas, permitindo que o teste
	 * valide exclusivamente a restrição de integridade do banco de dados.
	 *
	 * O erro esperado nesse cenário é:
	 * DataIntegrityViolationException
	 */
	@Test 
	@DisplayName("Save thorow DataIntegrityViolationException when name is null or 100<") 
	void save_DataIntegrityViolationException_WhenNameIsEnpty() { 
		Anime anime = new Anime(); 
	
	/*	Assertions.assertThatThrownBy(() -> {
	        animeRepository.save(anime);
	        animeRepository.flush();
	    }).isInstanceOf(DataIntegrityViolationException.class);
	*/
		Assertions.assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(() -> this.animeRepository.saveAndFlush(anime));
		
		Anime anime101 = Anime.builder().name("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").build();
		Assertions.assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(() -> this.animeRepository.saveAndFlush(anime101));
	
	}
	
	@Nested
	@DisplayName("teste de restricoes sobre a tabela anime")
	class collunmConstraints{
		@Autowired
		private AnimeRepository animeRepository;
		
		@Test
		@DisplayName("nullable = false - deve regeitar null")
		void deveRejeiratNull() {
			Anime anime = new Anime(); 
			
			Assertions.assertThatThrownBy(() -> {
		        animeRepository.save(anime);
		        animeRepository.flush();
		    }).isInstanceOf(DataIntegrityViolationException.class);
		}
		
		@Test
		@DisplayName(" length=100 - deve rejeitar nome com 100+ caracteres")
		void deveRefeitarComMaisQ100() {
			Anime anime = new Anime(); 
			
			Anime anime101 = Anime.builder().name("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").build();
			Assertions.assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(() -> this.animeRepository.saveAndFlush(anime101));
		
		}
		
		
	}
	

}
 