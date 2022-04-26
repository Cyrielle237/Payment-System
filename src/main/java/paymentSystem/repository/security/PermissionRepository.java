package paymentSystem.repository.security;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.security.Action;
import paymentSystem.security.Permission;
import paymentSystem.security.Role;

public interface PermissionRepository extends CrudRepository<Permission, String> {
	List<Permission> findByCodeOrRoleOrAction(String code, Role role, Action action);
	List<Permission> findByRole(Role role);
	Optional<Permission> findByCode(String code);
	void deleteByCode(String code);

}
