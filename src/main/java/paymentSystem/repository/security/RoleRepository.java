package paymentSystem.repository.security;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.security.Role;

public interface RoleRepository extends CrudRepository<Role, String> {
	List<Role> findByCodeOrDesignation(String code, String Designation);
	List<Role> findByDesignation(String Designation);
	Optional<Role> findByCode(String code);
	void deleteByCode(String code);

}
