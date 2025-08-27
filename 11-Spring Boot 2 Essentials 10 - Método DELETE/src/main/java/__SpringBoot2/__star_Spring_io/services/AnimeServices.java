package __SpringBoot2.__star_Spring_io.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import __SpringBoot2.__star_Spring_io.dominio.Anime;

@Service
public class AnimeServices {
	private static List<Anime> animes;
	static {
		animes=new ArrayList<Anime>(List.of(new Anime(1l, "DGB"),new Anime(2l, "bersek")));
	}
	
	public List<Anime> listAll() {
		return  animes;
	}
	
	public Anime findById(Long id) {
		return  animes.stream()
				.filter(anime-> id.equals(anime.getId()))
				.findFirst()
				.orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"id anime not foud"));
		
	}

	public static Anime save(Anime anime) {
		if(animes.size()==0) {
			anime.setId(1l);
		}
		if(animes.size()>0) {
			anime.setId(animes.get(animes.size()-1).getId()+1);
		}
		animes.add(anime);
		return anime;
	}

	public void delet(Long id) {
		 animes.remove(findById(id));
	}
}
