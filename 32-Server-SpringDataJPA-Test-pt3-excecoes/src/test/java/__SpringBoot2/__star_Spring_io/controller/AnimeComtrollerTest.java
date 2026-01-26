package __SpringBoot2.__star_Spring_io.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import __SpringBoot2.__star_Spring_io.dominio.Anime;
import __SpringBoot2.__star_Spring_io.services.AnimeServices;
import __SpringBoot2.__star_Spring_io.util.AnimeCreator;
import __SpringBoot2.__star_Spring_io.util.DateUtil;

//@ExtendWith(SpringExtension.class) // faz o teste com junit/spring
//@ExtendWith(MockitoExtension.class)
class AnimeComtrollerTest {

	
	@Mock  //cria objeto falso q sumula o comportamento da classe real (no podemos definir este comportamento)
	private AnimeServices animeServices;
	@Mock  //cria objeto falso q sumula o comportamento da classe real (no podemos definir este comportamento)
	private DateUtil dateUtil;
	
	
	@InjectMocks //cria uma istancia real da classe porem q utilisara os mocks abaixo:
	private AnimeComtroller animeComtroller;
	
	@BeforeEach
	void setUp() {
			MockitoAnnotations.openMocks(this);

			PageImpl<Anime> animePage = new PageImpl<Anime>(List.of(AnimeCreator.createValidAnime()));
			
			BDDMockito.when(animeServices.listAll(ArgumentMatchers.any()))
						.thenReturn(animePage);	
			
			BDDMockito.when(dateUtil.formatLocalDataTimeToDatabaseStyle(any()))
						  .thenReturn(
								  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
						   );
			
		
	} 
	
	
	@Test
	@DisplayName("list returne Liste Of Animes Inside Page Object when successful")
	void list_returneListeOfAnimesInsidePageObject_whensuccessful() {
		String expectedName = AnimeCreator.createAnimeToBeSaved().getName();
		
		Page<Anime> animepages = animeComtroller.list(null).getBody();
		
		Assertions.assertThat(animepages).isNotNull();
		Assertions.assertThat(animepages.toList())
									.isNotNull()
									.hasSize(1);
		Assertions.assertThat(animepages.toList().get(0).getName()).isEqualTo(expectedName);
		
	}

}
