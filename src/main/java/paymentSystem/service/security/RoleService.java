package paymentSystem.service.security;

import java.util.List;

import paymentSystem.security.Role;

public interface RoleService {

	Role createRole(Role r);

	Role getRoleByCode(String code);

	List<Role> getAllRoles();

	void deleteRole(String code);

	Role updateRole(Role r);

}
