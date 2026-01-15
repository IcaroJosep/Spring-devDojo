package __SpringBoot2.__star_Spring_io.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import __SpringBoot2.__star_Spring_io.dominio.Anime;
import __SpringBoot2.__star_Spring_io.requests.AnimePostRequestBody;
import __SpringBoot2.__star_Spring_io.requests.AnimePutRequestBody;

//(componentModel = "spring") e INTANCE tem a msm serventia porem intance pode ser usado como metodo statico da clase para chamada de outros metodos
//prefira ussar @Mapper quando for grandes projetos por comvençao

@Mapper(componentModel = "spring")//ingeta dependencia no String para ussar vc precisa atribuir uma istancia "private final AnimeMapper"
public interface AnimeMapper {
    
    Anime toAnime(AnimePostRequestBody animePostRequestBody);
    
    Anime toAnime(AnimePutRequestBody animePutRequestBody);
}

/*@Mapper // REMOVA componentModel = "spring"
public interface AnimeMapper {
    
    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
    
    Anime toAnime(AnimePostRequestBody animePostRequestBody);
    
    Anime toAnime(AnimePutRequestBody animePutRequestBody);
}*/
