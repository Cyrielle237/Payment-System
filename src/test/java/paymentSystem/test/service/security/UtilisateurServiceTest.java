package paymentSystem.test.service.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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
import paymentSystem.entity.rh.Categorie;
import paymentSystem.entity.rh.Echelon;
import paymentSystem.entity.rh.Personnel;
import paymentSystem.repository.security.UtilisateurRepository;
import paymentSystem.security.Utilisateur;
import paymentSystem.service.security.UtilisateurServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UtilisateurServiceTest {

	private UtilisateurServiceImpl sut;

	@Mock 
	private UtilisateurRepository userRepo;

	private Utilisateur user1;
	private List<Utilisateur> userList = new  ArrayList<>();

	@BeforeEach
	public void init() {
		Categorie categorie = Categorie.builder().code("1369640790466").designation("11").build() ;
		Echelon echelon = Echelon.builder().code("1369641072669").description("A").build() ;
		Personnel personnel = Personnel.builder().code("1369640790466").matricule("092022").nom("MOUKOKO")
				.dateNaissance(LocalDate.of(1990,  10, 10)).datePriseService(LocalDate.of(2022, 1, 1)).categorie(categorie)
				.echelon(echelon).dureeContrat(2).build() ;
		sut = new UtilisateurServiceImpl(userRepo);
		user1 = Utilisateur.builder().code("136964079046").login("Cycy").password("admin").personnel(personnel).build();
		userList.add(user1);
	}

	@AfterEach
	public void dropall() {
		user1 = null;
		userList = null;
	}


	@Test
	@DisplayName("Enregistrement d'un nouvel utilisateur")
	public void tester_createUtilisateur() {
		when(userRepo.save(user1)).thenReturn(user1);
		Utilisateur u = sut.createUtilisateur(user1);
		assertThat(u).isSameAs(user1);
		verify(userRepo, times(1)).save(user1);
	}


	@Test
	@DisplayName("Test desuppression d'un user existant")
	public void tester_deleteUtilisateur_Exists() {
		when(userRepo.findByCode(user1.getCode())).thenReturn(Optional.of(user1));
		doNothing().when(userRepo).deleteByCode(user1.getCode());
		assertDoesNotThrow(() -> sut.deleteUtilisateur(user1.getCode()));
		verify(userRepo, times(1)).deleteByCode(user1.getCode());
	}


	@Test
	@DisplayName("Test de suppression d'un role inexistant")
	public void tester_deleteUtilisateur_DONOTExists() {
		when(userRepo.findByCode(user1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.deleteUtilisateur(user1.getCode()));
		verify(userRepo, times(1)).findByCode(user1.getCode());
	}


	@Test
	@DisplayName("Test de mise à jour d'un user existant")
	public void tester_updateUtilisateur_Exists() {
		when(userRepo.findByCode(user1.getCode())).thenReturn(Optional.of(user1));
		when(userRepo.save(user1)).thenReturn(user1);
		Utilisateur u = sut.updateUtilisateur(user1);
		assertThat(u).isEqualTo(user1);
		assertDoesNotThrow(() -> sut.updateUtilisateur(user1));
		verify(userRepo, times(2)).findByCode(user1.getCode());

	}

	@Test
	@DisplayName("Test de mise à jour d'un user inexistant")
	public void tester_updateUtilisateur_DONOTExists() {
		when(userRepo.findByCode(user1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class , ()-> sut.updateUtilisateur(user1));
		verify(userRepo, times(1)).findByCode(user1.getCode());

	}

	@Test
	@DisplayName("Test de récupération de la liste exixstante des utilisateurs")
	public void tester_getAllUtilisateurs_ListExists() {
		when(userRepo.findAll()).thenReturn(userList);
		List<Utilisateur> users = sut.getAllUtilisateurs();
		assertDoesNotThrow(() -> sut.getAllUtilisateurs());
		assertEquals(users, userList);
		verify(userRepo, times(2)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'une liste vide de users")
	public void tester_getAllUtilisateurs_ListDONOTExists() {
		when(userRepo.findAll()).thenReturn(Collections.emptyList());
		assertThrows(ElementNotFoundException.class, () -> sut.getAllUtilisateurs());
		verify(userRepo, times(1)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'un user exixtant par son code")
	public void tester_getUtilisateurByCode_Exists() {
		when(userRepo.findByCode(user1.getCode())).thenReturn(Optional.of(user1));
		Utilisateur u = sut.getUtilisateurByCode(user1.getCode());
		assertEquals(u, user1);
		assertDoesNotThrow(() -> sut.getUtilisateurByCode(user1.getCode()));
		verify(userRepo, times(2)).findByCode(user1.getCode());

	}


	@Test
	@DisplayName("Test de récupération d'un role inexixtant  par son code")
	public void tester_getUtilisateurByCode_DONOTExists() {
		when(userRepo.findByCode(user1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.getUtilisateurByCode(user1.getCode()));
		verify(userRepo, times(1)).findByCode(user1.getCode());
	}


}
