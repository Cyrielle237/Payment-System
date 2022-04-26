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
import paymentSystem.repository.security.PermissionRepository;
import paymentSystem.security.Action;
import paymentSystem.security.Permission;
import paymentSystem.security.Role;
import paymentSystem.service.security.PermissionServiceImpl;


@ExtendWith(MockitoExtension.class)
public class PermissionServiceTest {

	private PermissionServiceImpl sut;

	@Mock 
	private PermissionRepository permRepo;

	private Permission perm1;
	private List<Permission> permList = new  ArrayList<>();

	@BeforeEach
	public void init() {
		Action action = Action.builder().code("1369640790466").designation("ENREGISTRER UNE AGENCE")
				.methode("create").entite("Agence").build();
		Role role = Role.builder().code("1371227153640").designation("SUPER ADMINISTRATEUR").build();
		sut = new PermissionServiceImpl(permRepo);
		perm1 = Permission.builder().code("1369640790466").action(action).role(role).build();		
		permList.add(perm1);
		permList.add(perm1);
	}

	@AfterEach
	public void dropall() {
		perm1 = null;
		permList = null;
	}


	@Test
	@DisplayName("Enregistrement d'une nouvelle permission")
	public void tester_createPermission() {
		when(permRepo.save(perm1)).thenReturn(perm1);
		Permission p = sut.createPermission(perm1);
		assertThat(p).isSameAs(perm1);
		verify(permRepo, times(1)).save(perm1);
	}


	@Test
	@DisplayName("Test desuppression d'une permission existante")
	public void tester_deletePermission_Exists() {
		when(permRepo.findByCode(perm1.getCode())).thenReturn(Optional.of(perm1));
		doNothing().when(permRepo).deleteByCode(perm1.getCode());
		assertDoesNotThrow(() -> sut.deletePermission(perm1.getCode()));
		verify(permRepo, times(1)).deleteByCode(perm1.getCode());
	}


	@Test
	@DisplayName("Test de suppression d'une permission inexistante")
	public void tester_deletePermission_DONOTExists() {
		when(permRepo.findByCode(perm1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.deletePermission(perm1.getCode()));
		verify(permRepo, times(1)).findByCode(perm1.getCode());
	}


	@Test
	@DisplayName("Test de mise à jour d'une permission existante")
	public void tester_updatePermission_Exists() {
		when(permRepo.findByCode(perm1.getCode())).thenReturn(Optional.of(perm1));
		when(permRepo.save(perm1)).thenReturn(perm1);
		Permission p = sut.updatePermission(perm1);
		assertThat(p).isEqualTo(perm1);
		assertDoesNotThrow(() -> sut.updatePermission(perm1));
		verify(permRepo, times(2)).findByCode(perm1.getCode());

	}

	@Test
	@DisplayName("Test de mise à jour d'une permission inexistante")
	public void tester_updatePermission_DONOTExists() {
		when(permRepo.findByCode(perm1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class , ()-> sut.updatePermission(perm1));
		verify(permRepo, times(1)).findByCode(perm1.getCode());

	}

	@Test
	@DisplayName("Test de récupération de la liste exixstante de permissions")
	public void tester_getAllPermissions_ListExists() {
		when(permRepo.findAll()).thenReturn(permList);
		List<Permission> perm = sut.getAllPermissions();
		assertDoesNotThrow(() -> sut.getAllPermissions());
		assertEquals(perm, permList);
		verify(permRepo, times(2)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'une liste vide de permissions")
	public void tester_getAllPermissions_ListDONOTExists() {
		when(permRepo.findAll()).thenReturn(Collections.emptyList());
		assertThrows(ElementNotFoundException.class, () -> sut.getAllPermissions());
		verify(permRepo, times(1)).findAll();
	}

	@Test
	@DisplayName("Test de récupération de permission exixtante par son code")
	public void tester_getPermByCode_Exists() {
		when(permRepo.findByCode(perm1.getCode())).thenReturn(Optional.of(perm1));
		Permission p = sut.getPermissionByCode(perm1.getCode());
		assertEquals(p, perm1);
		assertDoesNotThrow(() -> sut.getPermissionByCode(perm1.getCode()));
		verify(permRepo, times(2)).findByCode(perm1.getCode());

	}


	@Test
	@DisplayName("Test de récupération d'une permission inexixtante par son code")
	public void tester_getPermByCode_DONOTExists() {
		when(permRepo.findByCode(perm1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.getPermissionByCode(perm1.getCode()));
		verify(permRepo, times(1)).findByCode(perm1.getCode());
	}



}
