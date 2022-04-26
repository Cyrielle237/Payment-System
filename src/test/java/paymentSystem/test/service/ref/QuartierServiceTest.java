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
import paymentSystem.entity.ref.Quartier;
import paymentSystem.entity.ref.Ville;
import paymentSystem.repository.ref.QuartierRepository;
import paymentSystem.service.ref.QuartierServiceImpl;

@ExtendWith(MockitoExtension.class)
public class QuartierServiceTest {

	private QuartierServiceImpl sut;

	@Mock
	private QuartierRepository quartierRepo;

	private Quartier q1;
	private List<Quartier> qqList = new ArrayList<>();


	@BeforeEach
	public void init() {
		Ville ville = Ville.builder().code("137208229057").designation("Yaounde").build();
		sut = new QuartierServiceImpl(quartierRepo);
		q1 =Quartier.builder().code("137208229057").designation("Emana").ville(ville).build();
		qqList.add(q1);
	}

	@AfterEach
	public void dropall() {
		q1 = null;
		qqList = null;
	}


	@Test
	@DisplayName("Test de la creation d'un nouveau quartier")
	public void tester_creationBanque() {
		when(quartierRepo.save(q1)).thenReturn(q1);
		Quartier b = sut.createQuartier(q1);
		assertThat(b).isSameAs(q1);
		verify(quartierRepo, times(1)).save(q1);
	}

	@Test
	@DisplayName("Test desuppression d'un quartier existant")
	public void tester_deleteQuartier_thatExists() {
		when(quartierRepo.findByCode(q1.getCode())).thenReturn(Optional.of(q1));
		doNothing().when(quartierRepo).deleteByCode(q1.getCode());
		assertDoesNotThrow(() -> sut.deleteQuartier(q1.getCode()));
		verify(quartierRepo, times(1)).deleteByCode(q1.getCode());
	}

	@Test
	@DisplayName("Tester la suppression d'un quartier inexistant")
	public void tester_deleteQuartier_thatDONOTExists() {
		when(quartierRepo.findByCode(q1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.deleteQuartier(q1.getCode()));
		verify(quartierRepo, times(1)).findByCode(q1.getCode());
	}


	@Test
	@DisplayName("Test de mise à jour d'un quartier existant")
	public void tester_updateBanque_thatExists() {
		when(quartierRepo.findByCode(q1.getCode())).thenReturn(Optional.of(q1));
		when(quartierRepo.save(q1)).thenReturn(q1);
		Quartier q = sut.updateQuartier(q1);
		assertThat(q).isEqualTo(q1);
		assertDoesNotThrow(() -> sut.updateQuartier(q1));
		verify(quartierRepo, times(2)).findByCode(q1.getCode());

	}

	@Test
	@DisplayName("Test de mise à jour d'un quartier inexistant")
	public void tester_updateQuartier_thatDONOTExists() {
		when(quartierRepo.findByCode(q1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class , ()-> sut.updateQuartier(q1));
		verify(quartierRepo, times(1)).findByCode(q1.getCode());

	}

	@Test
	@DisplayName("Test de récupération de la liste exixstante des quartiers")
	public void tester_getAllBanques_ListExists() {
		when(quartierRepo.findAll()).thenReturn(qqList);
		List<Quartier> q = sut.getAllQuartiers();
		assertDoesNotThrow(() -> sut.getAllQuartiers());
		assertEquals(q, qqList);
		verify(quartierRepo, times(2)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'une liste vide des quartiers")
	public void tester_getAllvs_ListDONOTExists() {
		when(quartierRepo.findAll()).thenReturn(Collections.emptyList());
		assertThrows(ElementNotFoundException.class, () -> sut.getAllQuartiers());
		verify(quartierRepo, times(1)).findAll();
	}

	@Test
	@DisplayName("Test de récupération du quartier exixtant par son code")
	public void tester_getQuartierByCode_Exists() {
		when(quartierRepo.findByCode(q1.getCode())).thenReturn(Optional.of(q1));
		Quartier q = sut.getQuartierByCode(q1.getCode());
		assertEquals(q, q1);
		assertDoesNotThrow(() -> sut.getQuartierByCode(q1.getCode()));
		verify(quartierRepo, times(2)).findByCode(q1.getCode());

	}


	@Test
	@DisplayName("Test de récupération du quartier inexixtant par son code")
	public void tester_getQuartierByCode_DONOTExists() {
		when(quartierRepo.findByCode(q1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.getQuartierByCode(q1.getCode()));
		verify(quartierRepo, times(1)).findByCode(q1.getCode());
	}

}
