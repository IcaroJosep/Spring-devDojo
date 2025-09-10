package __SpringBoot2.__star_Spring_io.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import __SpringBoot2.__star_Spring_io.dominio.Anime;

public interface AnimeRepository extends JpaRepository<Anime, Long>{
	/*extends JpaRepository<Anime, Long>:
						O primeiro tipo (Anime) é a entidade.
						O segundo tipo (Long) é o tipo da chave primária (@Id) da entidade.
						O Spring Data gera automaticamente a implementação em tempo de execução
						e registra como bean no contexto (não precisa @Repository).*/

/*O que já vem pronto com JpaRepository 
Alguns métodos úteis que você ganha sem escrever nada:
save(anime) / saveAll(...)
findById(id) / findAll() / findAll(Pageable) / findAll(Sort)
deleteById(id) / delete(anime) / deleteAll()
count() / existsById(id)*/	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	List<Anime> findByNameContaining(String name);
	List<Anime> findByName(String name);
/*O Spring Data JPA consegue interpretar o nome do método (findByName) e gerar automaticamente a query equivalente:
		SELECT * FROM anime WHERE name = ?;
	
	findBy → indica que é uma busca.
	Name → tem que ser exatamente igual ao nome do atributo na entidade Anime
	
	O N maiúsculo em Name é apenas convencional para separar palavras no método (findBy + Name).
	O Spring mapeia automaticamente para o atributo name da entidade, mesmo que a primeira letra seja minúscula.
*/
}
