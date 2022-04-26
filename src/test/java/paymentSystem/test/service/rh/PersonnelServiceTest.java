package paymentSystem.test.service.rh;

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
import paymentSystem.repository.rh.PersonnelRepository;
import paymentSystem.service.rh.PersonnelServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PersonnelServiceTest {
	private PersonnelServiceImpl sut;

	@Mock 
	private PersonnelRepository personnelRepo;

	private Personnel pers1;
	private List<Personnel> persList = new  ArrayList<>();

	@BeforeEach
	public void init() {
		sut = new PersonnelServiceImpl(personnelRepo);
		Categorie categorie = Categorie.builder().code("1369640790466").designation("11").build();
		Echelon echelon = Echelon.builder().code("1369641072669").description("A").build();
		pers1 = Personnel.builder().code("1369640790466").matricule("092022").nom("MOUKOKO").prenom("Gilbert")
				.dateNaissance(LocalDate.of(1990,  10, 10)).datePriseService(LocalDate.of(2022, 1, 1))
				.categorie(categorie).echelon(echelon).dureeContrat(2).build();
		persList.add(pers1);
	}

	@AfterEach
	public void dropall() {
		pers1 = null;
		persList = null;
	}


	@Test
	@DisplayName("Enregistrement d'un nouveau personnel")
	public void tester_createPersonnel() {
		when(personnelRepo.save(pers1)).thenReturn(pers1);
		Personnel p = sut.createPersonnel(pers1);
		assertThat(p).isSameAs(pers1);
		verify(personnelRepo, times(1)).save(pers1);
	}


	@Test
	@DisplayName("Test desuppression d'un personnel existant")
	public void tester_deletePersonnel_personnelExists() {
		when(personnelRepo.findByCode(pers1.getCode())).thenReturn(Optional.of(pers1));
		doNothing().when(personnelRepo).deleteByCode(pers1.getCode());
		assertDoesNotThrow(() -> sut.deletePersonnel(pers1.getCode()));
		verify(personnelRepo, times(1)).deleteByCode(pers1.getCode());
	}


	@Test
	@DisplayName("Test de suppression d'un personnel inexistant")
	public void tester_deletePersonnel_personnelDONOTExists() {
		when(personnelRepo.findByCode(pers1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.deletePersonnel(pers1.getCode()));
		verify(personnelRepo, times(1)).findByCode(pers1.getCode());
	}


	@Test
	@DisplayName("Test de mise à jour d'un personnel existant")
	public void tester_updatePersonnel_persExists() {
		when(personnelRepo.findByCode(pers1.getCode())).thenReturn(Optional.of(pers1));
		when(personnelRepo.save(pers1)).thenReturn(pers1);
		Personnel p = sut.updatePersonnel(pers1);
		assertThat(p).isEqualTo(pers1);
		assertDoesNotThrow(() -> sut.updatePersonnel(pers1));
		verify(personnelRepo, times(2)).findByCode(pers1.getCode());

	}

	@Test
	@DisplayName("Test de mise à jour d'un personnel inexistant")
	public void tester_updatePersonnel_persDONOTExists() {
		when(personnelRepo.findByCode(pers1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class , ()-> sut.updatePersonnel(pers1));
		verify(personnelRepo, times(1)).findByCode(pers1.getCode());

	}

	@Test
	@DisplayName("Test de récupération de la liste exixstante de categories")
	public void tester_getAllCategories_ListExists() {
		when(personnelRepo.findAll()).thenReturn(persList);
		List<Personnel> p = sut.getAllPersonnels();
		assertDoesNotThrow(() -> sut.getAllPersonnels());
		assertEquals(p, persList);
		verify(personnelRepo, times(2)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'une liste vide de personnels")
	public void tester_getAllPersonnels_ListDONOTExists() {
		when(personnelRepo.findAll()).thenReturn(Collections.emptyList());
		assertThrows(ElementNotFoundException.class, () -> sut.getAllPersonnels());
		verify(personnelRepo, times(1)).findAll();
	}

	@Test
	@DisplayName("Test de récupération de personnel exixtant par son code")
	public void tester_getPersByCode_thatExists() {
		when(personnelRepo.findByCode(pers1.getCode())).thenReturn(Optional.of(pers1));
		Personnel p = sut.getPersonnelByCode(pers1.getCode());
		assertEquals(p, pers1);
		assertDoesNotThrow(() -> sut.getPersonnelByCode(pers1.getCode()));
		verify(personnelRepo, times(2)).findByCode(pers1.getCode());

	}


	@Test
	@DisplayName("Test de récupération de personnel inexixtant par son code")
	public void tester_getCatByCode_catDONOTExists() {
		when(personnelRepo.findByCode(pers1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.getPersonnelByCode(pers1.getCode()));
		verify(personnelRepo, times(1)).findByCode(pers1.getCode());
	}


}
