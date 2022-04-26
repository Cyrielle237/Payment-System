package paymentSystem.test.service.rh;

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
import paymentSystem.entity.rh.Fonction;
import paymentSystem.repository.rh.FonctionRepository;
import paymentSystem.service.rh.FonctionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class FonctionServiceTest {
	private FonctionServiceImpl sut;

	@Mock
	private FonctionRepository fonctionRepo;

	private Fonction fct1, fct2;
	private List<Fonction> fctList = new ArrayList<>();


	@BeforeEach
	public void init() {
		sut = new FonctionServiceImpl(fonctionRepo);
		fct1 = Fonction.builder().code("1371134011934").designation("DIRECTEUR GENERAL").build();
		fct2 = Fonction.builder().code("1371134011995").designation("DIRECTEUR GENERAL ADJOINT").build();
		fctList.add(fct1);
		fctList.add(fct2);
	}

	@AfterEach
	public void dropall() {
		fct1 = fct2 = null;
		fctList = null;
	}


	@Test
	@DisplayName("Test de ma creation d'une nouvelle fonction")
	public void tester_creationFonction() {
		when(fonctionRepo.save(fct1)).thenReturn(fct1);
		Fonction f = sut.createFonction(fct1);
		assertThat(f).isSameAs(fct1);
		verify(fonctionRepo, times(1)).save(fct1);
	}

	@Test
	@DisplayName("Test desuppression d'une fonction existante")
	public void tester_deleteFonction_thatExists() {
		when(fonctionRepo.findByCode(fct1.getCode())).thenReturn(Optional.of(fct1));
		doNothing().when(fonctionRepo).deleteByCode(fct1.getCode());
		assertDoesNotThrow(() -> sut.deleteFonction(fct1.getCode()));
		verify(fonctionRepo, times(1)).deleteByCode(fct1.getCode());
	}

	@Test
	@DisplayName("Tester la suppression d'une fonction inexistante")
	public void tester_deleteFonction_thatDONOTExists() {
		when(fonctionRepo.findByCode(fct1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.deleteFonction(fct1.getCode()));
		verify(fonctionRepo, times(1)).findByCode(fct1.getCode());
	}


	@Test
	@DisplayName("Test de mise à jour d'une fonction existante")
	public void tester_updateFonction_thatExists() {
		when(fonctionRepo.findByCode(fct1.getCode())).thenReturn(Optional.of(fct1));
		when(fonctionRepo.save(fct1)).thenReturn(fct1);
		Fonction f = sut.updateFonction(fct1);
		assertThat(f).isEqualTo(fct1);
		assertDoesNotThrow(() -> sut.updateFonction(fct1));
		verify(fonctionRepo, times(2)).findByCode(fct1.getCode());

	}

	@Test
	@DisplayName("Test de mise à jour d'une fonction inexistante")
	public void tester_updateFonction_thatDONOTExists() {
		when(fonctionRepo.findByCode(fct1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class , ()-> sut.updateFonction(fct1));
		verify(fonctionRepo, times(1)).findByCode(fct1.getCode());

	}

	@Test
	@DisplayName("Test de récupération de la liste exixstante des fonctions")
	public void tester_getAllFonctions_ListExists() {
		when(fonctionRepo.findAll()).thenReturn(fctList);
		List<Fonction> fct = sut.getAllFonctions();
		assertDoesNotThrow(() -> sut.getAllFonctions());
		assertEquals(fct, fctList);
		verify(fonctionRepo, times(2)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'une liste vide de fonctions")
	public void tester_getAllFonctions_ListDONOTExists() {
		when(fonctionRepo.findAll()).thenReturn(Collections.emptyList());
		assertThrows(ElementNotFoundException.class, () -> sut.getAllFonctions());
		verify(fonctionRepo, times(1)).findAll();
	}

	@Test
	@DisplayName("Test de récupération de la fonction exixtante par son code")
	public void tester_getFctByCode_catExists() {
		when(fonctionRepo.findByCode(fct1.getCode())).thenReturn(Optional.of(fct1));
		Fonction f = sut.getFonctionByCode(fct1.getCode());
		assertEquals(f, fct1);
		assertDoesNotThrow(() -> sut.getFonctionByCode(fct1.getCode()));
		verify(fonctionRepo, times(2)).findByCode(fct1.getCode());

	}


	@Test
	@DisplayName("Test de récupération de la fonction inexixtante par son code")
	public void tester_getFctByCode_catDONOTExists() {
		when(fonctionRepo.findByCode(fct1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.getFonctionByCode(fct1.getCode()));
		verify(fonctionRepo, times(1)).findByCode(fct1.getCode());
	}



}
