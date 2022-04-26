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
import paymentSystem.entity.ref.Banque;
import paymentSystem.repository.ref.BanqueRepository;
import paymentSystem.service.ref.BanqueServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BanqueServiceTest {

	private BanqueServiceImpl sut;

	@Mock
	private BanqueRepository banqueRepo;

	private Banque b1;
	private List<Banque> bList = new ArrayList<>();


	@BeforeEach
	public void init() {
		sut = new BanqueServiceImpl(banqueRepo);
		b1 =Banque.builder().code("137208229057").designation("direction generale").build();
		bList.add(b1);
	}

	@AfterEach
	public void dropall() {
		b1 = null;
		bList = null;
	}


	@Test
	@DisplayName("Test de ma creation d'une nouvelle banque")
	public void tester_creationBanque() {
		when(banqueRepo.save(b1)).thenReturn(b1);
		Banque b = sut.createBanque(b1);
		assertThat(b).isSameAs(b1);
		verify(banqueRepo, times(1)).save(b1);
	}

	@Test
	@DisplayName("Test desuppression d'une banque existante")
	public void tester_deleteBanque_thatExists() {
		when(banqueRepo.findByCode(b1.getCode())).thenReturn(Optional.of(b1));
		doNothing().when(banqueRepo).deleteByCode(b1.getCode());
		assertDoesNotThrow(() -> sut.deleteBanque(b1.getCode()));
		verify(banqueRepo, times(1)).deleteByCode(b1.getCode());
	}

	@Test
	@DisplayName("Tester la suppression d'une banque inexistante")
	public void tester_deletBanque_thatDONOTExists() {
		when(banqueRepo.findByCode(b1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.deleteBanque(b1.getCode()));
		verify(banqueRepo, times(1)).findByCode(b1.getCode());
	}


	@Test
	@DisplayName("Test de mise à jour d'une banque existante")
	public void tester_updateBanque_thatExists() {
		when(banqueRepo.findByCode(b1.getCode())).thenReturn(Optional.of(b1));
		when(banqueRepo.save(b1)).thenReturn(b1);
		Banque b = sut.updateBanque(b1);
		assertThat(b).isEqualTo(b1);
		assertDoesNotThrow(() -> sut.updateBanque(b1));
		verify(banqueRepo, times(2)).findByCode(b1.getCode());

	}

	@Test
	@DisplayName("Test de mise à jour d'une banque inexistante")
	public void tester_updateBanque_thatDONOTExists() {
		when(banqueRepo.findByCode(b1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class , ()-> sut.updateBanque(b1));
		verify(banqueRepo, times(1)).findByCode(b1.getCode());

	}

	@Test
	@DisplayName("Test de récupération de la liste exixstante des banques")
	public void tester_getAllBanques_ListExists() {
		when(banqueRepo.findAll()).thenReturn(bList);
		List<Banque> b = sut.getAllBanques();
		assertDoesNotThrow(() -> sut.getAllBanques());
		assertEquals(b, bList);
		verify(banqueRepo, times(2)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'une liste vide des banques")
	public void tester_getAllBanques_ListDONOTExists() {
		when(banqueRepo.findAll()).thenReturn(Collections.emptyList());
		assertThrows(ElementNotFoundException.class, () -> sut.getAllBanques());
		verify(banqueRepo, times(1)).findAll();
	}

	@Test
	@DisplayName("Test de récupération de la banque exixtante par son code")
	public void tester_getBanqueByCode_Exists() {
		when(banqueRepo.findByCode(b1.getCode())).thenReturn(Optional.of(b1));
		Banque b = sut.getBanqueByCode(b1.getCode());
		assertEquals(b, b1);
		assertDoesNotThrow(() -> sut.getBanqueByCode(b1.getCode()));
		verify(banqueRepo, times(2)).findByCode(b1.getCode());

	}


	@Test
	@DisplayName("Test de récupération de la banque inexixtante par son code")
	public void tester_getBanqueByCode_DONOTExists() {
		when(banqueRepo.findByCode(b1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.getBanqueByCode(b1.getCode()));
		verify(banqueRepo, times(1)).findByCode(b1.getCode());
	}

}
