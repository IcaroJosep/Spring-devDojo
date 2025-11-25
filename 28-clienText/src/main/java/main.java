import java.net.http.HttpHeaders;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;



/*	OBS: este arquivo "27-testclient" e um ppprogrto mevem pporem sem sring boot
 * 
 */


@Log4j2
public class main {
	
	public static void main(String[] args) {
		
	
		
		
		RestTemplate restTemplate = new RestTemplate();
		
		AnimePostRequestBody body = new AnimePostRequestBody();
		body.setName("caralho28p1");
		
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<AnimePostRequestBody> requestEntity = new HttpEntity<>(body,headers);

		ResponseEntity<Anime> response = restTemplate.exchange(
				"http://localhost:8080/animes",
		        HttpMethod.POST,
		        requestEntity,
		        Anime.class
				);        
		Anime animeSalvo = response.getBody();
		log.info("Criado: " + animeSalvo);
	}
	
}
