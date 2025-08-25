package __SpringBoot2.__star_Spring_io.services;

import java.util.List;

import org.springframework.stereotype.Service;

import __SpringBoot2.__star_Spring_io.dominio.Anime;

@Service
public class AnimeServices {
	public List<Anime> listAll() {
		return  List.of(new Anime(12l, "DGB"),new Anime(12l, "bersek"));
	}

}
