package paymentSystem.service.security;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.repository.security.RoleRepository;
import paymentSystem.security.Role;

public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepo;

	public RoleServiceImpl(RoleRepository roleRepo) {
		this.roleRepo = roleRepo;
	}


	@Override
	public Role createRole(Role r) {
		return roleRepo.save(r);
	}



	@Override
	public Role updateRole(Role r) {
		Role role = roleRepo.findByCode(r.getCode())
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Role not found", String.valueOf(r.getCode()))));
		role.setDesignation(r.getDesignation());
		role.setDescription(r.getDescription());
		return roleRepo.save(role);
	}


	@Override
	public void deleteRole(String code) {
		Optional<Role> role = roleRepo.findByCode(code);
		if(role.isPresent())
			roleRepo.deleteByCode(code);
		else
			throw new ElementNotFoundException(String.format
					("Role not found", String.valueOf(code)));
	}



	@Override
	public Role getRoleByCode(String code) {
		Role role = roleRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Role with ID %s not found",String.valueOf(code)))) ;
		return role;
	}


	@Override
	public List<Role> getAllRoles() {
		List<Role> listRoles = (List<Role>) roleRepo.findAll();
		if(listRoles.size() >= 1) {
			return listRoles;
		}else
			throw new ElementNotFoundException();				
	}


}
