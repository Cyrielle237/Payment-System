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
import paymentSystem.entity.ref.Region;
import paymentSystem.repository.ref.RegionRepository;
import paymentSystem.service.ref.RegionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RegionServiceTest {

	private  RegionServiceImpl sut;

	@Mock
	private RegionRepository regionRepo;

	private Region r1;
	private List<Region> rList = new ArrayList<>();


	@BeforeEach
	public void init() {
		sut = new  RegionServiceImpl(regionRepo);
		r1 =Region.builder().code("137208229057").designation("CENTRE").build();
		rList.add(r1);
	}

	@AfterEach
	public void dropall() {
		r1 = null;
		rList = null;
	}


	@Test
	@DisplayName("Test de ma creation d'une nouvelle région")
	public void tester_creationRegion() {
		when(regionRepo.save(r1)).thenReturn(r1);
		Region r = sut.createRegion(r1);
		assertThat(r).isSameAs(r1);
		verify(regionRepo, times(1)).save(r1);
	}

	@Test
	@DisplayName("Test desuppression d'une region existante")
	public void tester_deleteRegion_thatExists() {
		when(regionRepo.findByCode(r1.getCode())).thenReturn(Optional.of(r1));
		doNothing().when(regionRepo).deleteByCode(r1.getCode());
		assertDoesNotThrow(() -> sut.deleteRegion(r1.getCode()));
		verify(regionRepo, times(1)).deleteByCode(r1.getCode());
	}

	@Test
	@DisplayName("Tester la suppression d'une region inexistante")
	public void tester_deletRegion_thatDONOTExists() {
		when(regionRepo.findByCode(r1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.deleteRegion(r1.getCode()));
		verify(regionRepo, times(1)).findByCode(r1.getCode());
	}


	@Test
	@DisplayName("Test de mise à jour d'une region existante")
	public void tester_updateRegion_thatExists() {
		when(regionRepo.findByCode(r1.getCode())).thenReturn(Optional.of(r1));
		when(regionRepo.save(r1)).thenReturn(r1);
		Region r = sut.updateRegion(r1);
		assertThat(r).isEqualTo(r1);
		assertDoesNotThrow(() -> sut.updateRegion(r1));
		verify(regionRepo, times(2)).findByCode(r1.getCode());

	}

	@Test
	@DisplayName("Test de mise à jour d'une region inexistante")
	public void tester_updateRegion_thatDONOTExists() {
		when(regionRepo.findByCode(r1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class , ()-> sut.updateRegion(r1));
		verify(regionRepo, times(1)).findByCode(r1.getCode());

	}

	@Test
	@DisplayName("Test de récupération de la liste exixstante des regions")
	public void tester_getAllRegions_ListExists() {
		when(regionRepo.findAll()).thenReturn(rList);
		List<Region> r = sut.getAllRegions();
		assertDoesNotThrow(() -> sut.getAllRegions());
		assertEquals(r, rList);
		verify(regionRepo, times(2)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'une liste vide des regions")
	public void tester_getAllRegions_ListDONOTExists() {
		when(regionRepo.findAll()).thenReturn(Collections.emptyList());
		assertThrows(ElementNotFoundException.class, () -> sut.getAllRegions());
		verify(regionRepo, times(1)).findAll();
	}

	@Test
	@DisplayName("Test de récupération de la region exixtante par son code")
	public void tester_getRegionByCode_Exists() {
		when(regionRepo.findByCode(r1.getCode())).thenReturn(Optional.of(r1));
		Region r = sut.getRegionByCode(r1.getCode());
		assertEquals(r, r1);
		assertDoesNotThrow(() -> sut.getRegionByCode(r1.getCode()));
		verify(regionRepo, times(2)).findByCode(r1.getCode());

	}


	@Test
	@DisplayName("Test de récupération de la region inexixtante par son code")
	public void tester_getRegionByCode_DONOTExists() {
		when(regionRepo.findByCode(r1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.getRegionByCode(r1.getCode()));
		verify(regionRepo, times(1)).findByCode(r1.getCode());
	}

}
