package services;
import java.util.Scanner;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import dto.PageResponse;
import entity.Anime;
import lombok.extern.log4j.Log4j2;
import request.AnimePostRequestBody;


@Log4j2
public class services {

	protected static void findAll(Scanner scn) {
	
		final String BASE_URL = "http://localhost:8080/animes";
	
		int page = 0;
		int size = 5;
	
		while (true) {
			
			String urlRequest = BASE_URL + "?page=" + page + "&size=" + size + "&sort=name,asc";
	
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<PageResponse<Anime>> response = restTemplate.exchange(urlRequest, HttpMethod.GET, null,
					new ParameterizedTypeReference<PageResponse<Anime>>() {
					});
			PageResponse<Anime> pageResponse = response.getBody();
	
			if (pageResponse == null) {
				System.out.println("Não foi possível carregar a página.");
				break;
			}
			System.out.println("\n-------------------------------------");
			System.out.println("Página " + (pageResponse.getNumber()+1) + " de " + pageResponse.getTotalPages());
			System.out.println("-------------------------------------");
	
			for (Anime a : pageResponse.getContent()) {
				System.out.println(a);
			}
			if (pageResponse.isLast()) {
				System.out.println("\n-- Fim das páginas. --");
				break;
			}
			System.out.print("\nAvançar para a próxima página? (s/n): ");
			String opcao = scn.nextLine().trim().toLowerCase();
	
			if (!opcao.equals("s")) {
				break;
			}
	
			page++; // avança para a próxima página
		}
	
	}

	protected static void addAnime(String name) {
		RestTemplate restTemplate = new RestTemplate();
	
		AnimePostRequestBody body = new AnimePostRequestBody();
		body.setName(name);
	
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	
		HttpEntity<AnimePostRequestBody> requestEntity = new HttpEntity<>(body, headers);
	
		ResponseEntity<Anime> response = restTemplate.exchange("http://localhost:8080/animes", HttpMethod.POST,
				requestEntity, Anime.class);
		Anime animeSalvo = response.getBody();
		log.info("Criado: " + animeSalvo);
	}

}