package __SpringBoot2.__star_Spring_io.util;

import __SpringBoot2.__star_Spring_io.dominio.Anime;

public class  AnimeCreator {

 public static Anime createAnimeToBeSaved() {
	return Anime.builder().name("komg").build();
 }

 public static Anime createValidAnime() {
	return Anime.builder().name("komg").id(1l).build();
 }

 public static Anime createValidUpdatedAnime() {
	return Anime.builder().name("komg 2").id(1l).build();
 }
 
}
