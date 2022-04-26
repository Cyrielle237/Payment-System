package paymentSystem.test.service.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.repository.security.RoleRepository;
import paymentSystem.security.Role;
import paymentSystem.service.security.RoleServiceImpl;


@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

	private RoleServiceImpl sut;

	@Mock 
	private RoleRepository roleRepo;

	private Role role1;
	private List<Role> roleList = new  ArrayList<>();

	@BeforeEach
	public void init() {
		sut = new RoleServiceImpl(roleRepo);
		role1 = Role.builder().code("1371227153640").designation("SUPER ADMINISTRATEUR").build();
		roleList.add(role1);
	}

	@AfterEach
	public void dropall() {
		role1 = null;
		roleList = null;
	}


	@Test
	@DisplayName("Enregistrement d'un nouveau role")
	public void tester_createRole() {
		when(roleRepo.save(role1)).thenReturn(role1);
		Role r = sut.createRole(role1);
		assertThat(r).isSameAs(role1);
		verify(roleRepo, times(1)).save(role1);
	}


	@Test
	@DisplayName("Test desuppression d'un role existant")
	public void tester_deleteRole_Exists() {
		when(roleRepo.findByCode(role1.getCode())).thenReturn(Optional.of(role1));
		doNothing().when(roleRepo).deleteByCode(role1.getCode());
		assertDoesNotThrow(() -> sut.deleteRole(role1.getCode()));
		verify(roleRepo, times(1)).deleteByCode(role1.getCode());
	}


	@Test
	@DisplayName("Test de suppression d'un role inexistant")
	public void tester_deleteRole_DONOTExists() {
		when(roleRepo.findByCode(role1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.deleteRole(role1.getCode()));
		verify(roleRepo, times(1)).findByCode(role1.getCode());
	}


	@Test
	@DisplayName("Test de mise à jour d'un role existant")
	public void tester_updateRole_Exists() {
		when(roleRepo.findByCode(role1.getCode())).thenReturn(Optional.of(role1));
		when(roleRepo.save(role1)).thenReturn(role1);
		Role r = sut.updateRole(role1);
		assertThat(r).isEqualTo(role1);
		assertDoesNotThrow(() -> sut.updateRole(role1));
		verify(roleRepo, times(2)).findByCode(role1.getCode());

	}

	@Test
	@DisplayName("Test de mise à jour d'un role inexistant")
	public void tester_updateRole_DONOTExists() {
		when(roleRepo.findByCode(role1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class , ()-> sut.updateRole(role1));
		verify(roleRepo, times(1)).findByCode(role1.getCode());

	}

	@Test
	@DisplayName("Test de récupération de la liste exixstante de roles")
	public void tester_getAllRoles_ListExists() {
		when(roleRepo.findAll()).thenReturn(roleList);
		List<Role> roles = sut.getAllRoles();
		assertDoesNotThrow(() -> sut.getAllRoles());
		assertEquals(roles, roleList);
		verify(roleRepo, times(2)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'une liste vide de roles")
	public void tester_getAllRoles_ListDONOTExists() {
		when(roleRepo.findAll()).thenReturn(Collections.emptyList());
		assertThrows(ElementNotFoundException.class, () -> sut.getAllRoles());
		verify(roleRepo, times(1)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'un role exixtant par son code")
	public void tester_getRoleByCode_Exists() {
		when(roleRepo.findByCode(role1.getCode())).thenReturn(Optional.of(role1));
		Role r = sut.getRoleByCode(role1.getCode());
		assertEquals(r, role1);
		assertDoesNotThrow(() -> sut.getRoleByCode(role1.getCode()));
		verify(roleRepo, times(2)).findByCode(role1.getCode());

	}


	@Test
	@DisplayName("Test de récupération d'un role inexixtant  par son code")
	public void tester_getRoleByCode_DONOTExists() {
		when(roleRepo.findByCode(role1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.getRoleByCode(role1.getCode()));
		verify(roleRepo, times(1)).findByCode(role1.getCode());
	}

}
