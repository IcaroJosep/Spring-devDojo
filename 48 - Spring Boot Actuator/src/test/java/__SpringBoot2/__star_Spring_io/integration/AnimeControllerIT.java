package __SpringBoot2.__star_Spring_io.integration;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import __SpringBoot2.__star_Spring_io.controller.AnimeComtroller;
import __SpringBoot2.__star_Spring_io.dominio.Anime;
import __SpringBoot2.__star_Spring_io.dominio.DevDojoUser;
import __SpringBoot2.__star_Spring_io.repository.AnimeRepository;
import __SpringBoot2.__star_Spring_io.repository.DevDojoUserRepository;
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
	@Qualifier(value = "testRestTemplateRoleUserCreator")
	private TestRestTemplate testRestTemplateUser;
	@Autowired
	@Qualifier(value = "testRestTemplateRoleAdmCreator")
	private TestRestTemplate testRestTemplateAdm;

	// opcao de captura de porta relativa a "TestRestTemplate"
	//@LocalServerPort
	// private int port;

	// Injetamos o repositório para podermos preparar o banco de dados (inserir
	// dados)
	// antes de testar se a API consegue buscá-los.
	@Autowired
	private AnimeRepository animeRepository;

	@Autowired
	private DevDojoUserRepository devDojoUserRepository;
	
	
	@TestConfiguration
	@Lazy
	static class Config{
				@Bean(name="testRestTemplateRoleUserCreator")
				public TestRestTemplate testRestTemplateRoleUserCreator(@Value("${local.server.port}") int port) {
					
				RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
														.rootUri("http://localHost:"+port)
														.basicAuthentication("davdojo", "academyDB");
					
					return new TestRestTemplate(restTemplateBuilder);
				}
	
				@Bean(name="testRestTemplateRoleAdmCreator")
				public TestRestTemplate testRestTemplateRoleAdmCreator(@Value("${local.server.port}") int port) {
					
				RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
														.rootUri("http://localHost:"+port)
														.basicAuthentication("icaro", "academyDB");
					
					return new TestRestTemplate(restTemplateBuilder);
				}
	}
	
	
	
		@BeforeEach
		void bancoUsuarios() {
			DevDojoUser user = DevDojoUser.builder()
					.name("davdojo")
					.username("davdojo")
					.password("{bcrypt}$2a$10$H/ckSoZSOULnxnaSjCaPJuoS/4dyIb79pa6iNmdbMnlZWRZ34wlYe")
					.authorities("ROLE_USER")
					.build();
			DevDojoUser userAdm = DevDojoUser.builder()
					.name("icaro")
					.username("icaro")
					.password("{bcrypt}$2a$10$H/ckSoZSOULnxnaSjCaPJuoS/4dyIb79pa6iNmdbMnlZWRZ34wlYe")
					.authorities("ROLE_USER,ROLE_ADMIN")
					.build();
			
			devDojoUserRepository.saveAll(List.of(user, userAdm));
			
			
		}
	
	
	
	
	
	
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
		ResponseEntity<PageableResponse<Anime>> pagina = testRestTemplateUser.exchange("/animes", HttpMethod.GET, null, 
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
	@Test
	@DisplayName("test integração exemplo")
	void test2() {
		// PREPARAÇÃO (Given): Salvamos um anime diretamente no banco para ter o que
		// buscar.s
		// O saveAndFlush garante que o dado seja gravado imediatamente.
		animeRepository.saveAndFlush(Anime.builder().name("caralho").build());

		// EXECUÇÃO (When): Fazemos uma requisição GET para a rota "/animes".
		// O 'exchange' é usado aqui porque o retorno é uma classe genérica
		// (PageableResponse).
		// Ele converte o JSON que a API responde em um objeto Java automaticamente.
		ResponseEntity<PageableResponse<Anime>> pagina = testRestTemplateAdm.exchange("/animes", HttpMethod.GET, null, 
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