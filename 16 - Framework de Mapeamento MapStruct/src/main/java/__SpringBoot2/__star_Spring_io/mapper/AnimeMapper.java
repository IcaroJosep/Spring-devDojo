package __SpringBoot2.__star_Spring_io.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import __SpringBoot2.__star_Spring_io.dominio.Anime;
import __SpringBoot2.__star_Spring_io.requests.AnimePostRequestBody;
import __SpringBoot2.__star_Spring_io.requests.AnimePutRequestBody;

//@mapper e INTANCE tem a msm serventia porem intance pode ser usado como metodo statico da clase para chamada de outros metodos
//prefira ussar @Mapper quando for grandes projetos por comvençao

@Mapper(componentModel = "spring")//ingeta dependencia no String para ussar vc precisa atribuir uma istancia "private final AnimeMapper"
public abstract class AnimeMapper {
	public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);//ussa a istancia como metodo statico da clase
	
	public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);
	
	public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);

}
