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
		

		
		RestTemplate restTemplate = new RestTemplate();
		
		AnimePost anime = new AnimePost(); 
		anime.setName("caralho");
		
        Anime response = restTemplate.postForObject(
                        "http://localhost:8080/animes",	
                        anime,
                        Anime.class
                );
        log.info(response);
        		
       
        
      
        
	}
	
}
