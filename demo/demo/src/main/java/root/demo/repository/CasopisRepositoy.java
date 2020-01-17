package root.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import root.demo.model.Casopis;

@Repository
public interface CasopisRepositoy extends JpaRepository<Casopis, Long> {

	Casopis findOneByIssn(String issn);

	//@Query(value = "SELECT * FROM casopis WHERE issn=?1")
	//Casopis getByISSN(String s);
}
