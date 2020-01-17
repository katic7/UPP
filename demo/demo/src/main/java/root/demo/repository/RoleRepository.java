package root.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import root.demo.model.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
	@Query(value="select * from roles where naziv=?1", nativeQuery=true)
	Roles getRoleByName(String str);
}
