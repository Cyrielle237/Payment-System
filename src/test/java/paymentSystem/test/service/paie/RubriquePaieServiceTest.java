package paymentSystem.test.service.paie;

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
import paymentSystem.entity.paie.RubriquePaie;
import paymentSystem.repository.paie.RubriquePaieRepository;
import paymentSystem.service.paie.RubriquePaieServiceImpl;


@ExtendWith(MockitoExtension.class)
public class RubriquePaieServiceTest {

	private RubriquePaieServiceImpl sut;

	@Mock 
	private RubriquePaieRepository rubriqueRepo;

	private RubriquePaie rubrique1;
	private List<RubriquePaie> rubriqueList = new  ArrayList<>();

	@BeforeEach
	public void init() {
		sut = new RubriquePaieServiceImpl(rubriqueRepo);
		rubrique1 = RubriquePaie.builder().code("1371288006660").designation("SALAIRE DE BASE")
				.type("1").cotisable("NON COTISABLE").taxable("NON TAXABLE").taux(0.1).partPatronale("0")
				.partSalariale("1").build();
		rubriqueList.add(rubrique1);
	}

	@AfterEach
	public void dropall() {
		rubrique1 = null;
		rubriqueList = null;
	}


	@Test
	@DisplayName("Enregistrement d'une nouvelle rubrique de calcul")
	public void tester_createRubriquePaie() {
		when(rubriqueRepo.save(rubrique1)).thenReturn(rubrique1);
		RubriquePaie r = sut.createRubriquePaie(rubrique1);
		assertThat(r).isSameAs(rubrique1);
		verify(rubriqueRepo, times(1)).save(rubrique1);
	}


	@Test
	@DisplayName("Test desuppression d'une rubrique existante")
	public void tester_deleteRubriquePaie_Exists() {
		when(rubriqueRepo.findByCode(rubrique1.getCode())).thenReturn(Optional.of(rubrique1));
		doNothing().when(rubriqueRepo).deleteByCode(rubrique1.getCode());
		assertDoesNotThrow(() -> sut.deleteRubriquePaie(rubrique1.getCode()));
		verify(rubriqueRepo, times(1)).deleteByCode(rubrique1.getCode());
	}


	@Test
	@DisplayName("Test de suppression d'une rubrique inexistante")
	public void tester_deleteRubriquePaie_DONOTExists() {
		when(rubriqueRepo.findByCode(rubrique1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.deleteRubriquePaie(rubrique1.getCode()));
		verify(rubriqueRepo, times(1)).findByCode(rubrique1.getCode());
	}


	@Test
	@DisplayName("Test de mise à jour d'une rubrique existante")
	public void tester_updateRubriquePaie_Exists() {
		when(rubriqueRepo.findByCode(rubrique1.getCode())).thenReturn(Optional.of(rubrique1));
		when(rubriqueRepo.save(rubrique1)).thenReturn(rubrique1);
		RubriquePaie r = sut.updateRubriquePaie(rubrique1);
		assertThat(r).isEqualTo(rubrique1);
		assertDoesNotThrow(() -> sut.updateRubriquePaie(rubrique1));
		verify(rubriqueRepo, times(2)).findByCode(rubrique1.getCode());

	}

	@Test
	@DisplayName("Test de mise à jour d'une rubrique inexistante")
	public void tester_updateRubriquePaie_DONOTExists() {
		when(rubriqueRepo.findByCode(rubrique1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class , ()-> sut.updateRubriquePaie(rubrique1));
		verify(rubriqueRepo, times(1)).findByCode(rubrique1.getCode());

	}

	@Test
	@DisplayName("Test de récupération de la liste exixstante de rubriques")
	public void tester_getAllRubriquePaies_ListExists() {
		when(rubriqueRepo.findAll()).thenReturn(rubriqueList);
		List<RubriquePaie> r = sut.getAllRubriquePaies();
		assertDoesNotThrow(() -> sut.getAllRubriquePaies());
		assertEquals(r, rubriqueList);
		verify(rubriqueRepo, times(2)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'une liste vide de rubriques")
	public void tester_getAllRubriquePaies_ListDONOTExists() {
		when(rubriqueRepo.findAll()).thenReturn(Collections.emptyList());
		assertThrows(ElementNotFoundException.class, () -> sut.getAllRubriquePaies());
		verify(rubriqueRepo, times(1)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'une rubrique exixtante par son code")
	public void tester_getRubriquePaieByCode_Exists() {
		when(rubriqueRepo.findByCode(rubrique1.getCode())).thenReturn(Optional.of(rubrique1));
		RubriquePaie r = sut.getRubriquePaieByCode(rubrique1.getCode());
		assertEquals(r, rubrique1);
		assertDoesNotThrow(() -> sut.getRubriquePaieByCode(rubrique1.getCode()));
		verify(rubriqueRepo, times(2)).findByCode(rubrique1.getCode());

	}


	@Test
	@DisplayName("Test de récupération d'une rubrique inexixtante  par son code")
	public void tester_getRubriquePaieByCode_DONOTExists() {
		when(rubriqueRepo.findByCode(rubrique1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.getRubriquePaieByCode(rubrique1.getCode()));
		verify(rubriqueRepo, times(1)).findByCode(rubrique1.getCode());
	}


}
