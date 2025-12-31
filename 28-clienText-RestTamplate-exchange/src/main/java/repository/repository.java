package repository;

import java.util.Scanner;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import dto.PageResponse;
import entity.Anime;

public class repository {
	public static void executePaginatedRequest(Scanner scn, String baseUrl, String name, Boolean comtem) {
		int page = 0;
		int size = 5;

		while (true) {
			// Construção SEGURA da URL
			String urlRequest = buildSafeUrl(baseUrl, page, size, name, comtem);
			PageResponse<Anime> pageResponse = executeApiCall(urlRequest);

			if (!processPageResponse(pageResponse, scn)) {
				break;
			}

			if (!shouldContinueToNextPage(scn, pageResponse)) {
				break;
			}

			page++;
		}
	}

	private static String buildSafeUrl(String baseUrl, int page, int size, String name, Boolean comtem) {
		
		UriComponentsBuilder builder = UriComponentsBuilder.
				fromHttpUrl(baseUrl).
				queryParam("page", page).
				queryParam("size", size).
				queryParam("sort", "name,asc");

		// Adiciona parâmetros condicionalmente e com encoding automático
		if (name != null && !name.trim().isEmpty()) {
			builder.queryParam("name", name.trim());
		}

		if (comtem != null) {
			builder.queryParam("comtem", comtem);
		}

		return builder.toUriString();
	}

	private static PageResponse<Anime> executeApiCall(String urlRequest) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<PageResponse<Anime>> response = restTemplate.exchange(
				urlRequest, 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<PageResponse<Anime>>() {}
				);
		return response.getBody();
	}

	private static boolean processPageResponse(PageResponse<Anime> pageResponse, Scanner scn) {
		if (pageResponse == null) {
			System.out.println("Não foi possível carregar a página.");
			return false;
		}

		System.out.println("\n-------------------------------------");
		System.out.println("Página " + (pageResponse.getNumber() + 1) + " de " + pageResponse.getTotalPages());
		System.out.println("-------------------------------------");

		for (Anime a : pageResponse.getContent()) {
			System.out.println(a);
		}

		if (pageResponse.isLast()) {
			System.out.println("\n-- Fim das páginas. --");
			return false;
		}

		return true;
	}

	private static boolean shouldContinueToNextPage(Scanner scn, PageResponse<Anime> pageResponse) {
		System.out.print("\nAvançar para a próxima página? (s/n): ");
		String opcao = scn.nextLine().trim().toLowerCase();
		return opcao.equals("s");
	}
}
