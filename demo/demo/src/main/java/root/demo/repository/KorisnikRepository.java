package root.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import root.demo.model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	
	//@Query(value = "SELECT * FROM korisnik WHERE korisnik.email=?1", nativeQuery = true)
	//Korisnik getByEmail(String email);
	
	Korisnik findOneByEmail(String s);
}
