package paymentSystem.service.security;

import java.util.List;

import paymentSystem.security.Permission;

public interface PermissionService {

	Permission createPermission(Permission p);

	Permission getPermissionByCode(String code);

	List<Permission> getAllPermissions();

	void deletePermission(String code);

	Permission updatePermission(Permission p);

}
