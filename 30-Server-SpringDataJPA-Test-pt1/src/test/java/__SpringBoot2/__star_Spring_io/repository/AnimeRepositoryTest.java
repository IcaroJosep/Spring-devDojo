package __SpringBoot2.__star_Spring_io.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import __SpringBoot2.__star_Spring_io.dominio.Anime;


@DataJpaTest
//@DisplayName("testes para o repositorio animes")
class AnimeRepositoryTest {
	
	@Autowired
	private AnimeRepository animeRepository;
	
	@Test
	@DisplayName("Save Creates anime when Successfull")
	void save_PersistenciaAnime_WhenSuccessful() {
		Anime animeToBeSaved  = createAnime();
		Anime animeSaved = this.animeRepository.save(animeToBeSaved);
		
		Assertions.assertThat(animeSaved).isNotNull();
		Assertions.assertThat(animeSaved.getId()).isNotNull();
		Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());
	}
	
	private Anime createAnime() {
		return Anime.builder().name("zica full").build();
	}
	

}
 