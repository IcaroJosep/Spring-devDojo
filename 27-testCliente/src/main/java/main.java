import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;



/*	OBS: este arquivo "27-testclient" e um ppprogrto mevem pporem sem sring boot
 * 
 */


@Log4j2
public class main {
	
	public static void main(String[] args) {
		
	/*reqquisiçao para retorno de objeto anime , testado com api da aula 14-spring data jpa */
		
	/*	ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/2", Anime.class);
		
		log.info(entity);
		
		Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/2", Anime.class);
		log.info(object);
	*/
		
		/* requisiçao para retorno de page (emcapsulando um um objeto "pageRespponse", testado com arquivo 27-Restempplate.... )*/
		
		RestTemplate restTemplate = new RestTemplate();
		
        ResponseEntity<PageResponse<Anime>> response =
                restTemplate.exchange(
                        "http://localhost:8080/animes?page=0&size=5&sort=id,desc",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<PageResponse<Anime>>() {}
                );
        
        PageResponse<Anime> page = response.getBody();
        
        log.info("Total elementos: " + page.getTotalElements());
        log.info("Conteúdo: " + page.getContent());
        
        
        
	}
	
}
