package __SpringBoot2.__star_Spring_io.seguranca;

import org.jsoup.*;
import org.jsoup.safety.Safelist;

import __SpringBoot2.__star_Spring_io.dominio.Anime;


public class Sanatizador {
		

	public static String saniString(String input) {
		if (input==null) {
			return null;
		}
		 String htmlseguro=Jsoup.clean(input, Safelist.none());
			
		 
		 String textoPuro = Jsoup.parse(htmlseguro).text();
		 
		 if (textoPuro.length() > 100) {
	            textoPuro = textoPuro.substring(0, 100);
	        }
		 
		return textoPuro;
	}
	
	public static Anime saniAnime(Anime anime) {
        if (anime == null) return null;
        
        return Anime.builder()
            .id(anime.getId())
            .name(Sanatizador.saniString(anime.getName())) // Sanitiza na saída
            .build();
    }
	
	
}
