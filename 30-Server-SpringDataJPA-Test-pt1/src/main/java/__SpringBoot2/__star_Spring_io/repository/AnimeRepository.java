package __SpringBoot2.__star_Spring_io.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import __SpringBoot2.__star_Spring_io.dominio.Anime;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
	Page<Anime> findByNameContaining(String name, Pageable pageable);

	Page<Anime> findByName(String name, Pageable pageable);
}
