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
import paymentSystem.entity.rh.Echelon;
import paymentSystem.repository.rh.EchelonRepository;
import paymentSystem.service.rh.EchelonServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EchelonServiceTest {

	private EchelonServiceImpl sut;

	@Mock
	private EchelonRepository echelonRepo;

	private Echelon ech1, ech2;
	private List<Echelon> echList = new ArrayList<>();


	@BeforeEach
	public void init() {
		sut = new EchelonServiceImpl(echelonRepo);
		ech1 = Echelon.builder().code("1369641072669").designation("A").build();
		ech2 = Echelon.builder().code("1369641073132").designation("B").build();
		echList.add(ech1);
		echList.add(ech2);
	}

	@AfterEach
	public void dropall() {
		ech1 = ech2 = null;
		echList = null;
	}


	@Test
	@DisplayName("Test de ma creation d'un nouvel echelon")
	public void tester_creationEchelon() {
		when(echelonRepo.save(ech1)).thenReturn(ech1);
		Echelon e = sut.createEchelon(ech1);
		assertThat(e).isSameAs(ech1);
		verify(echelonRepo, times(1)).save(ech1);
	}

	@Test
	@DisplayName("Test desuppression d'un echelon existant")
	public void tester_deleteEchelon_thatExists() {
		when(echelonRepo.findByCode(ech1.getCode())).thenReturn(Optional.of(ech1));
		doNothing().when(echelonRepo).deleteByCode(ech1.getCode());
		assertDoesNotThrow(() -> sut.deleteEchelon(ech1.getCode()));
		verify(echelonRepo, times(1)).deleteByCode(ech1.getCode());
	}

	@Test
	@DisplayName("Tester la suppression d'un echelon inexistant")
	public void tester_deleteEchelon_thatDONOTExists() {
		when(echelonRepo.findByCode(ech1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.deleteEchelon(ech1.getCode()));
		verify(echelonRepo, times(1)).findByCode(ech1.getCode());
	}


	@Test
	@DisplayName("Test de mise à jour d'un echelon existant")
	public void tester_updateEchelon_thatExists() {
		when(echelonRepo.findByCode(ech1.getCode())).thenReturn(Optional.of(ech1));
		when(echelonRepo.save(ech1)).thenReturn(ech1);
		Echelon e = sut.updateEchelon(ech1);
		assertThat(e).isEqualTo(ech1);
		assertDoesNotThrow(() -> sut.updateEchelon(ech1));
		verify(echelonRepo, times(2)).findByCode(ech1.getCode());

	}

	@Test
	@DisplayName("Test de mise à jour d'un echelon inexistant")
	public void tester_updateEchelon_thatDONOTExists() {
		when(echelonRepo.findByCode(ech1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class , ()-> sut.updateEchelon(ech1));
		verify(echelonRepo, times(1)).findByCode(ech1.getCode());

	}

	@Test
	@DisplayName("Test de récupération de la liste exixstante des echelons")
	public void tester_getAllEchelons_ListExists() {
		when(echelonRepo.findAll()).thenReturn(echList);
		List<Echelon> ech = sut.getAllEchelons();
		assertDoesNotThrow(() -> sut.getAllEchelons());
		assertEquals(ech, echList);
		verify(echelonRepo, times(2)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'une liste vide d'echelons")
	public void tester_getAllEchelons_ListDONOTExists() {
		when(echelonRepo.findAll()).thenReturn(Collections.emptyList());
		assertThrows(ElementNotFoundException.class, () -> sut.getAllEchelons());
		verify(echelonRepo, times(1)).findAll();
	}

	@Test
	@DisplayName("Test de récupération de l'echelon exixtant par son code")
	public void tester_getCatByCode_catExists() {
		when(echelonRepo.findByCode(ech1.getCode())).thenReturn(Optional.of(ech1));
		Echelon e = sut.getEchelonByCode(ech1.getCode());
		assertEquals(e, ech1);
		assertDoesNotThrow(() -> sut.getEchelonByCode(ech1.getCode()));
		verify(echelonRepo, times(2)).findByCode(ech1.getCode());

	}


	@Test
	@DisplayName("Test de récupération de l'echelon inexixtant par son code")
	public void tester_getCatByCode_catDONOTExists() {
		when(echelonRepo.findByCode(ech1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.getEchelonByCode(ech1.getCode()));
		verify(echelonRepo, times(1)).findByCode(ech1.getCode());
	}



}
