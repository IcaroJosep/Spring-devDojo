package __SpringBoot2.__star_Spring_io.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import __SpringBoot2.__star_Spring_io.dominio.Anime;
import __SpringBoot2.__star_Spring_io.requests.AnimePostRequestBody;
import __SpringBoot2.__star_Spring_io.requests.AnimePutRequestBody;

//(componentModel = "spring") e INTANCE tem a msm serventia porem intance pode ser usado como metodo statico da clase para chamada de outros metodos
//prefira ussar @Mapper quando for grandes projetos por comvençao

@Mapper//(componentModel = "spring")//ingeta dependencia no String para ussar vc precisa atribuir uma istancia "private final AnimeMapper"
public interface AnimeMapper {
	public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);//ussa a istancia como metodo statico da clase
	
	@Mapping(target = "id", ignore = true) // ignora o campo id
	public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);
	
	public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);

}/*
O que faz @Mapper
	Obrigatório sempre ✅
		Ele marca a interface/abstract class como um mapper e diz pro MapStruct gerar a implementação.
		Sem @Mapper, o MapStruct não gera o código de conversão (toAnime, etc.), então você teria que escrever manualmente.
2. O que faz (componentModel = "spring")
	Opcional ⚖️
		Se você colocar componentModel = "spring", o MapStruct registra o mapper como bean do Spring.
		Assim você pode injeção de dependência (private final AnimeMapper animeMapper;).
3. E se você usar INSTANCE?
	Mesmo com INSTANCE, você precisa do @Mapper, porque:
		O MapStruct ainda precisa saber que deve gerar a classe de implementação.
		O INSTANCE na verdade é só um atalho para chamar a classe gerada:
			public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
		Aqui o Mappers.getMapper(...) pega a implementação que o @Mapper mandou o MapStruct criar.
		
✅ Resumo final:
	@Mapper → sempre necessário, é o que dispara a geração do mapper.
	componentModel = "spring" → só se você quiser injeção do Spring.
	INSTANCE → funciona sem componentModel = "spring", mas ainda depende do @Mapper.*/
