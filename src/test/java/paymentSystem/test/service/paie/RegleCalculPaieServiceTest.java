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
import paymentSystem.entity.paie.RegleCalculPaie;
import paymentSystem.entity.paie.RubriquePaie;
import paymentSystem.repository.paie.RegleCalculPaieRepository;
import paymentSystem.service.paie.RegleCalculPaieServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RegleCalculPaieServiceTest {

	private RegleCalculPaieServiceImpl sut;

	@Mock 
	private RegleCalculPaieRepository regleRepo;

	private RegleCalculPaie regle1;
	private List<RegleCalculPaie> regleList = new  ArrayList<>();

	@BeforeEach
	public void init() {
		RubriquePaie rubrique = RubriquePaie.builder().code("1371288006660").designation("SALAIRE DE BASE")
				.type("1").cotisable("NON COTISABLE").taxable("NON TAXABLE").taux(0.1).partPatronale("0")
				.partSalariale("1").build();
		sut = new RegleCalculPaieServiceImpl(regleRepo);
		regle1 = RegleCalculPaie.builder().code("1371227153640").rubriquePaie(rubrique).formule("33000").build();
		regleList.add(regle1);
	}

	@AfterEach
	public void dropall() {
		regle1 = null;
		regleList = null;
	}


	@Test
	@DisplayName("Enregistrement d'une nouvelle regle de calcul")
	public void tester_createRegleCalculPaie() {
		when(regleRepo.save(regle1)).thenReturn(regle1);
		RegleCalculPaie r = sut.createRegleCalculPaie(regle1);
		assertThat(r).isSameAs(regle1);
		verify(regleRepo, times(1)).save(regle1);
	}


	@Test
	@DisplayName("Test desuppression d'une regle existante")
	public void tester_deleteRegleCalculPaie_Exists() {
		when(regleRepo.findByCode(regle1.getCode())).thenReturn(Optional.of(regle1));
		doNothing().when(regleRepo).deleteByCode(regle1.getCode());
		assertDoesNotThrow(() -> sut.deleteRegleCalculPaie(regle1.getCode()));
		verify(regleRepo, times(1)).deleteByCode(regle1.getCode());
	}


	@Test
	@DisplayName("Test de suppression d'une regle inexistante")
	public void tester_deleteRegleCalculPaie_DONOTExists() {
		when(regleRepo.findByCode(regle1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.deleteRegleCalculPaie(regle1.getCode()));
		verify(regleRepo, times(1)).findByCode(regle1.getCode());
	}


	@Test
	@DisplayName("Test de mise à jour d'une regle existante")
	public void tester_updateRegleCalculPaie_Exists() {
		when(regleRepo.findByCode(regle1.getCode())).thenReturn(Optional.of(regle1));
		when(regleRepo.save(regle1)).thenReturn(regle1);
		RegleCalculPaie r = sut.updateRegleCalculPaie(regle1);
		assertThat(r).isEqualTo(regle1);
		assertDoesNotThrow(() -> sut.updateRegleCalculPaie(regle1));
		verify(regleRepo, times(2)).findByCode(regle1.getCode());

	}

	@Test
	@DisplayName("Test de mise à jour d'une regle inexistante")
	public void tester_updateRegleCalculPaie_DONOTExists() {
		when(regleRepo.findByCode(regle1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class , ()-> sut.updateRegleCalculPaie(regle1));
		verify(regleRepo, times(1)).findByCode(regle1.getCode());

	}

	@Test
	@DisplayName("Test de récupération de la liste exixstante de regles")
	public void tester_getAllRegleCalculPaies_ListExists() {
		when(regleRepo.findAll()).thenReturn(regleList);
		List<RegleCalculPaie> regles = sut.getAllRegleCalculPaies();
		assertDoesNotThrow(() -> sut.getAllRegleCalculPaies());
		assertEquals(regles, regleList);
		verify(regleRepo, times(2)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'une liste vide de regles")
	public void tester_getAllRegleCalculPaies_ListDONOTExists() {
		when(regleRepo.findAll()).thenReturn(Collections.emptyList());
		assertThrows(ElementNotFoundException.class, () -> sut.getAllRegleCalculPaies());
		verify(regleRepo, times(1)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'une regle exixtante par son code")
	public void tester_getRegleCalculPaieByCode_Exists() {
		when(regleRepo.findByCode(regle1.getCode())).thenReturn(Optional.of(regle1));
		RegleCalculPaie r = sut.getRegleCalculPaieByCode(regle1.getCode());
		assertEquals(r, regle1);
		assertDoesNotThrow(() -> sut.getRegleCalculPaieByCode(regle1.getCode()));
		verify(regleRepo, times(2)).findByCode(regle1.getCode());

	}


	@Test
	@DisplayName("Test de récupération d'une regle inexixtante  par son code")
	public void tester_getRegleCalculPaieByCode_DONOTExists() {
		when(regleRepo.findByCode(regle1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.getRegleCalculPaieByCode(regle1.getCode()));
		verify(regleRepo, times(1)).findByCode(regle1.getCode());
	}

}
