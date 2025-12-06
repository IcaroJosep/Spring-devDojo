package services;

import java.util.Scanner;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import entity.Anime;
import lombok.extern.log4j.Log4j2;
import repository.repository;
import request.AnimePostRequestBody;

@Log4j2
public class services {

	protected static void findAll(Scanner scn) {

		final String BASE_URL = "http://localhost:8080/animes";
		repository.executePaginatedRequest(scn, BASE_URL, null, null);
	}

	protected static void findByName(String name, boolean comtem, Scanner scn) {
		final String BASE_URL = "http://localhost:8080/animes/findByName";
		repository.executePaginatedRequest(scn, BASE_URL, name, comtem);
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