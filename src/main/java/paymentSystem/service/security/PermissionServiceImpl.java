package paymentSystem.service.security;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.repository.security.PermissionRepository;
import paymentSystem.security.Permission;

public class PermissionServiceImpl implements PermissionService {

	private PermissionRepository permissionRepo;

	public PermissionServiceImpl(PermissionRepository permissionRepo) {
		this.permissionRepo = permissionRepo;
	}


	@Override
	public Permission createPermission(Permission p) {
		return permissionRepo.save(p);
	}



	@Override
	public Permission updatePermission(Permission p) {
		Permission perm = permissionRepo.findByCode(p.getCode())
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Permission not found", String.valueOf(p.getCode()))));
		perm.setRole(p.getRole());
		perm.setAction(p.getAction());
		return permissionRepo.save(perm);
	}



	@Override
	public void deletePermission(String code) {
		Optional<Permission> perm = permissionRepo.findByCode(code);
		if(perm.isPresent())
			permissionRepo.deleteByCode(code);
		else
			throw new ElementNotFoundException(String.format
					("Permission not found", String.valueOf(code)));
	}



	@Override
	public Permission getPermissionByCode(String code) {
		Permission perm = permissionRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Permission with ID %s not found",String.valueOf(code)))) ;
		return perm;
	}


	@Override
	public List<Permission> getAllPermissions() {
		List<Permission> listPerm = (List<Permission>) permissionRepo.findAll();
		if(listPerm.size() >= 1) {
			return listPerm;
		}else
			throw new ElementNotFoundException();				
	}

}
