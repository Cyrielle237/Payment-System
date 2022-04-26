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
import paymentSystem.entity.ref.Ville;
import paymentSystem.repository.ref.VilleRepository;
import paymentSystem.service.ref.VilleServiceImpl;

@ExtendWith(MockitoExtension.class)
public class VilleServiceTest {

	private  VilleServiceImpl sut;

	@Mock
	private VilleRepository villeRepo;

	private Ville v1;
	private List<Ville> vList = new ArrayList<>();


	@BeforeEach
	public void init() {
		sut = new  VilleServiceImpl(villeRepo);
		v1 =Ville.builder().code("137208229057").designation("YAOUNDE").build();
		vList.add(v1);
	}

	@AfterEach
	public void dropall() {
		v1 = null;
		vList = null;
	}


	@Test
	@DisplayName("Test de ma creation d'une nouvelle ville")
	public void tester_creationVille() {
		when(villeRepo.save(v1)).thenReturn(v1);
		Ville v = sut.createVille(v1);
		assertThat(v).isSameAs(v1);
		verify(villeRepo, times(1)).save(v1);
	}

	@Test
	@DisplayName("Test desuppression d'une ville existante")
	public void tester_deleteVille_thatExists() {
		when(villeRepo.findByCode(v1.getCode())).thenReturn(Optional.of(v1));
		doNothing().when(villeRepo).deleteByCode(v1.getCode());
		assertDoesNotThrow(() -> sut.deleteVille(v1.getCode()));
		verify(villeRepo, times(1)).deleteByCode(v1.getCode());
	}

	@Test
	@DisplayName("Tester la suppression d'une ville inexistante")
	public void tester_deletRegion_thatDONOTExists() {
		when(villeRepo.findByCode(v1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.deleteVille(v1.getCode()));
		verify(villeRepo, times(1)).findByCode(v1.getCode());
	}


	@Test
	@DisplayName("Test de mise à jour d'une ville existante")
	public void tester_updateVille_thatExists() {
		when(villeRepo.findByCode(v1.getCode())).thenReturn(Optional.of(v1));
		when(villeRepo.save(v1)).thenReturn(v1);
		Ville r = sut.updateVille(v1);
		assertThat(r).isEqualTo(v1);
		assertDoesNotThrow(() -> sut.updateVille(v1));
		verify(villeRepo, times(2)).findByCode(v1.getCode());

	}

	@Test
	@DisplayName("Test de mise à jour d'une ville inexistante")
	public void tester_updateVille_thatDONOTExists() {
		when(villeRepo.findByCode(v1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class , ()-> sut.updateVille(v1));
		verify(villeRepo, times(1)).findByCode(v1.getCode());

	}

	@Test
	@DisplayName("Test de récupération de la liste exixstante des villes")
	public void tester_getAllVilles_ListExists() {
		when(villeRepo.findAll()).thenReturn(vList);
		List<Ville> v = sut.getAllVilles();
		assertDoesNotThrow(() -> sut.getAllVilles());
		assertEquals(v, vList);
		verify(villeRepo, times(2)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'une liste vide des villes")
	public void tester_getAllVilles_ListDONOTExists() {
		when(villeRepo.findAll()).thenReturn(Collections.emptyList());
		assertThrows(ElementNotFoundException.class, () -> sut.getAllVilles());
		verify(villeRepo, times(1)).findAll();
	}

	@Test
	@DisplayName("Test de récupération de la region exixtante par son code")
	public void tester_getVilleByCode_Exists() {
		when(villeRepo.findByCode(v1.getCode())).thenReturn(Optional.of(v1));
		Ville r = sut.getVilleByCode(v1.getCode());
		assertEquals(r, v1);
		assertDoesNotThrow(() -> sut.getVilleByCode(v1.getCode()));
		verify(villeRepo, times(2)).findByCode(v1.getCode());

	}


	@Test
	@DisplayName("Test de récupération de la region inexixtante par son code")
	public void tester_getRegionByCode_DONOTExists() {
		when(villeRepo.findByCode(v1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.getVilleByCode(v1.getCode()));
		verify(villeRepo, times(1)).findByCode(v1.getCode());
	}


}
