package __SpringBoot2.__star_Spring_io.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import __SpringBoot2.__star_Spring_io.dominio.Anime;

public interface AnimeRepository extends JpaRepository<Anime, Long>{
	List<Anime> findByNameContaining(String name);
	List<Anime> findByName(String name);

}
