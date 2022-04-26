package paymentSystem.test.service.ref;

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
import paymentSystem.entity.ref.Agence;
import paymentSystem.entity.ref.Ville;
import paymentSystem.repository.ref.AgenceRepository;
import paymentSystem.service.ref.AgenceServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AgenceServiceTest {

	private AgenceServiceImpl sut;

	@Mock
	private AgenceRepository agenceRepo;

	private Agence a1;
	private List<Agence> aList = new ArrayList<>();


	@BeforeEach
	public void init() {
		Ville ville = Ville.builder().code("137208229057").designation("Yaounde").build();
		sut = new AgenceServiceImpl(agenceRepo);
		a1 =Agence.builder().code("137208229057").designation("direction generale").ville(ville).build();
		aList.add(a1);
	}

	@AfterEach
	public void dropall() {
		a1 = null;
		aList = null;
	}


	@Test
	@DisplayName("Test de ma creation d'une nouvelle agence")
	public void tester_creationAgence() {
		when(agenceRepo.save(a1)).thenReturn(a1);
		Agence s= sut.createAgence(a1);
		assertThat(s).isSameAs(a1);
		verify(agenceRepo, times(1)).save(a1);
	}

	@Test
	@DisplayName("Test desuppression d'une agence existante")
	public void tester_deleteAgence_thatExists() {
		when(agenceRepo.findByCode(a1.getCode())).thenReturn(Optional.of(a1));
		doNothing().when(agenceRepo).deleteByCode(a1.getCode());
		assertDoesNotThrow(() -> sut.deleteAgence(a1.getCode()));
		verify(agenceRepo, times(1)).deleteByCode(a1.getCode());
	}

	@Test
	@DisplayName("Tester la suppression d'une agence inexistante")
	public void tester_deletAgence_thatDONOTExists() {
		when(agenceRepo.findByCode(a1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.deleteAgence(a1.getCode()));
		verify(agenceRepo, times(1)).findByCode(a1.getCode());
	}


	@Test
	@DisplayName("Test de mise à jour d'une agence existante")
	public void tester_updateAgence_thatExists() {
		when(agenceRepo.findByCode(a1.getCode())).thenReturn(Optional.of(a1));
		when(agenceRepo.save(a1)).thenReturn(a1);
		Agence s = sut.updateAgence(a1);
		assertThat(s).isEqualTo(a1);
		assertDoesNotThrow(() -> sut.updateAgence(a1));
		verify(agenceRepo, times(2)).findByCode(a1.getCode());

	}

	@Test
	@DisplayName("Test de mise à jour d'une agence inexistante")
	public void tester_updateAgence_thatDONOTExists() {
		when(agenceRepo.findByCode(a1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class , ()-> sut.updateAgence(a1));
		verify(agenceRepo, times(1)).findByCode(a1.getCode());

	}

	@Test
	@DisplayName("Test de récupération de la liste exixstante des agences")
	public void tester_getAllAgences_ListExists() {
		when(agenceRepo.findAll()).thenReturn(aList);
		List<Agence> a = sut.getAllAgences();
		assertDoesNotThrow(() -> sut.getAllAgences());
		assertEquals(a, aList);
		verify(agenceRepo, times(2)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'une liste vide des agences")
	public void tester_getAllAgences_ListDONOTExists() {
		when(agenceRepo.findAll()).thenReturn(Collections.emptyList());
		assertThrows(ElementNotFoundException.class, () -> sut.getAllAgences());
		verify(agenceRepo, times(1)).findAll();
	}

	@Test
	@DisplayName("Test de récupération de l'agence exixtante par son code")
	public void tester_getAgenceByCode_Exists() {
		when(agenceRepo.findByCode(a1.getCode())).thenReturn(Optional.of(a1));
		Agence a = sut.getAgenceByCode(a1.getCode());
		assertEquals(a, a1);
		assertDoesNotThrow(() -> sut.getAgenceByCode(a1.getCode()));
		verify(agenceRepo, times(2)).findByCode(a1.getCode());

	}


	@Test
	@DisplayName("Test de récupération de l'agence inexixtante par son code")
	public void tester_getAgenceByCode_DONOTExists() {
		when(agenceRepo.findByCode(a1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.getAgenceByCode(a1.getCode()));
		verify(agenceRepo, times(1)).findByCode(a1.getCode());
	}

}
