package main;



import java.awt.print.Printable;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import jakarta.validation.Valid;

public class MAIN {

	public static void main(String[] args) {
	//utilisando exchenge	
		
		//findAll
	 		System.out.println("\n\nfind All\n");
			String urlbase="http://localhost:8080/animes";
			String Page = "?page=0";
		
			RestTemplate findAll =  new RestTemplate();
			
			ResponseEntity<PageResponse<Anime>> responseFindAll = findAll.exchange(
					urlbase+Page,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<PageResponse<Anime>>() {}
					) ;
			
			PageResponse<Anime> response = responseFindAll.getBody();
			
			for(Anime a :response.getContent()) {
				System.out.println(a);
			}
			System.out.print("elementos por pagina:"+response.getSize()+"\ntotal de elementos: "+response.getTotalElements()+"\ntotal de paginas: "+response.getTotalPages()+"\npagina atual: "+(response.getNumber()+1));

	
		
		//findByName
			System.out.println("\n\nfind By Name\n");
			
			String urlbase2="http://localhost:8080/animes/findByName";
			String Page2 = "?page=0&size=10&name=a&comtem=true";
		
			RestTemplate findByName =  new RestTemplate();
			
			ResponseEntity<PageResponse<Anime>> responseFindAll2 = findByName.exchange(
					urlbase2+Page2,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<PageResponse<Anime>>() {}
					) ;
			
			PageResponse<Anime> response2 = responseFindAll2.getBody();
			
			for(Anime a :response2.getContent()) {
				System.out.println(a);
			}
			System.out.print("elementos por pagina:"+response2.getSize()+"\ntotal de elementos: "+response2.getTotalElements()+"\ntotal de paginas: "+response2.getTotalPages()+"\npagina atual: "+(response2.getNumber()+1));
	
		//addAnime		
			
			System.out.println("\n\nAdd anime\n");
		
			RestTemplate requisicao = new RestTemplate();
			
			AnimePost animePost = new AnimePost();
			animePost.setName("yuu yuu hakusho");
			
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				
				HttpEntity<AnimePost> postEntity	= new HttpEntity<AnimePost>(animePost,headers);
				
				ResponseEntity<Anime> response3 = requisicao.exchange(
						"http://localhost:8080/animes",
						HttpMethod.POST,
						postEntity,
						Anime.class
						);
				
				Anime animesalvo = response3.getBody();
				
				System.out.println(animesalvo.getName()+" "+animesalvo.getId());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("nome invalido");
			}
			System.out.println("fim");
		
		//AtualizaAnime
			
			
			
			
			
			
			
		//deletaAnime
		
//////////////////////////////////////////////////////////////////////////////////////////////////////		
		
			
			
		
		
	}

}
