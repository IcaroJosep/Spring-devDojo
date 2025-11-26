import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import lombok.extern.log4j.Log4j2;

/*
 * 
 */

@Log4j2
public class main {

	public static void main(String[] args) {
		
		  int op = 0; Scanner scn = new Scanner(System.in);
		  
		  do {
		  
			  System.out.println("selecione a opcao desejada:\n1-adicionar anime \n2-listar todos\n0-sair"
			  );
			  
			  op = scn.nextInt(); 
			  switch (op) {
				  case 1: { 
					  	System.out.println("vc selecionol a opao 1,\n qual qual anime deseja adicionar : ");
					  	scn.nextLine(); addAnime(scn.nextLine()); 
				  		break; 
				  }
				  case 2 :{
					  scn.nextLine();
					  findAll(scn);
					  break;
				  }
				 
			  } 
		  } while (op != 0);
		 

		

	}

	private static void findAll(Scanner scn) {

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
			System.out.println("Página " + pageResponse.getNumber() + " de " + pageResponse.getTotalPages());
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

	private static void addAnime(String name) {
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
