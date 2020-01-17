package root.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import root.demo.model.NacinPlacanja;

@Repository
public interface NacinPlacanjaRepository extends JpaRepository<NacinPlacanja, Long> {
	
}
