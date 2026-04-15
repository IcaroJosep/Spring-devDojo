package __SpringBoot2.__star_Spring_io.integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import __SpringBoot2.__star_Spring_io.controller.AnimeComtroller;
import __SpringBoot2.__star_Spring_io.dominio.Anime;
import __SpringBoot2.__star_Spring_io.repository.AnimeRepository;
import __SpringBoot2.__star_Spring_io.wrapper.PageableResponse;

//Indica que é um teste do Spring Boot. O 'RANDOM_PORT' faz o servidor subir em uma porta 
//disponível aleatória, evitando conflitos caso você já tenha algo rodando na porta 8080.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

//Configura o Spring para usar um banco de dados de teste (geralmente em memória, como H2) 
//em vez do seu banco de dados de produção ou desenvolvimento.
@AutoConfigureTestDatabase

//Garante o isolamento: limpa e reseta o contexto do Spring (incluindo o banco) 
//ANTES de cada método de teste ser executado. Assim, um teste não interfere no outro.
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AnimeControllerIT {

	// O TestRestTemplate é um "cliente HTTP" do Spring usado para simular
	// requisições reais
	// (GET, POST, etc) para a sua API durante os testes.
	@Autowired
	private TestRestTemplate testRestTemplate;

	// opcao de captura de porta relativa a "TestRestTemplate"
	// @LocalServerPort
	// private int port;

	// Injetamos o repositório para podermos preparar o banco de dados (inserir
	// dados)
	// antes de testar se a API consegue buscá-los.
	@Autowired
	private AnimeRepository animeRepository;

	/**
	 * 
	 * @return sucesso se for retornada uma page contendo anime
	 * @author icaroub
	 *{@link AnimeComtroller#list(org.springframework.data.domain.Pageable)}.
	 */
	@Test
	@DisplayName("test integração exemplo")
	void test1() {
		// PREPARAÇÃO (Given): Salvamos um anime diretamente no banco para ter o que
		// buscar.
		// O saveAndFlush garante que o dado seja gravado imediatamente.
		animeRepository.saveAndFlush(Anime.builder().name("caralho").build());

		// EXECUÇÃO (When): Fazemos uma requisição GET para a rota "/animes".
		// O 'exchange' é usado aqui porque o retorno é uma classe genérica
		// (PageableResponse).
		// Ele converte o JSON que a API responde em um objeto Java automaticamente.
		ResponseEntity<PageableResponse<Anime>> pagina = testRestTemplate.exchange("/animes", HttpMethod.GET, null, 
				new ParameterizedTypeReference<PageableResponse<Anime>>() {
				});

		// VERIFICAÇÃO (Then): Usamos o Assertions (AssertJ) para checar se o resultado
		// é o esperado.
		
		
		Assertions.assertThat(pagina.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		// Verifica se a lista dentro da página não veio vazia.
		Assertions.assertThat(pagina.getBody().getContent()).isNotEmpty();

		// Verifica se o nome do primeiro anime da lista é exatamente o que salvamos.
		Assertions.assertThat(pagina.getBody().getContent().get(0).getName()).isEqualTo("caralho");
		
		
	}
}