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
import paymentSystem.entity.rh.Structure;
import paymentSystem.repository.rh.StructureRepository;
import paymentSystem.service.rh.StructureServiceImpl;


@ExtendWith(MockitoExtension.class)
public class StructureServiceTest {
	private StructureServiceImpl sut;

	@Mock
	private StructureRepository structureRepo;

	private Structure s1, s2;
	private List<Structure> sList = new ArrayList<>();


	@BeforeEach
	public void init() {
		sut = new StructureServiceImpl(structureRepo);
		s1 = Structure.builder().code("1301373230075").designation("CABINET DU PRESIDENT DU CONSEIL D'ADMINISTRATION").sigle("PCA").build();
		s2 = Structure.builder().code("1310001750153").designation("CELLULE DE LA TRADUCTION ET D INTERPRETARIAT").sigle("CTI").build();
		sList.add(s1);
		sList.add(s2);
	}

	@AfterEach
	public void dropall() {
		s1 = s2 = null;
		sList = null;
	}


	@Test
	@DisplayName("Test de ma creation d'une nouvelle Structure")
	public void tester_creationStructure() {
		when(structureRepo.save(s1)).thenReturn(s1);
		Structure s= sut.createStructure(s1);
		assertThat(s).isSameAs(s1);
		verify(structureRepo, times(1)).save(s1);
	}

	@Test
	@DisplayName("Test desuppression d'une structure existante")
	public void tester_deleteStructure_thatExists() {
		when(structureRepo.findByCode(s1.getCode())).thenReturn(Optional.of(s1));
		doNothing().when(structureRepo).deleteByCode(s1.getCode());
		assertDoesNotThrow(() -> sut.deleteStructure(s1.getCode()));
		verify(structureRepo, times(1)).deleteByCode(s1.getCode());
	}

	@Test
	@DisplayName("Tester la suppression d'une structure inexistante")
	public void tester_deletStructure_thatDONOTExists() {
		when(structureRepo.findByCode(s1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.deleteStructure(s1.getCode()));
		verify(structureRepo, times(1)).findByCode(s1.getCode());
	}


	@Test
	@DisplayName("Test de mise à jour d'une structure existante")
	public void tester_updateStructure_thatExists() {
		when(structureRepo.findByCode(s1.getCode())).thenReturn(Optional.of(s1));
		when(structureRepo.save(s1)).thenReturn(s1);
		Structure s = sut.updateStructure(s1);
		assertThat(s).isEqualTo(s1);
		assertDoesNotThrow(() -> sut.updateStructure(s1));
		verify(structureRepo, times(2)).findByCode(s1.getCode());

	}

	@Test
	@DisplayName("Test de mise à jour d'une structure inexistante")
	public void tester_updateStructure_thatDONOTExists() {
		when(structureRepo.findByCode(s1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class , ()-> sut.updateStructure(s1));
		verify(structureRepo, times(1)).findByCode(s1.getCode());

	}

	@Test
	@DisplayName("Test de récupération de la liste exixstante des structures")
	public void tester_getAllStructures_ListExists() {
		when(structureRepo.findAll()).thenReturn(sList);
		List<Structure> s = sut.getAllStructures();
		assertDoesNotThrow(() -> sut.getAllStructures());
		assertEquals(s, sList);
		verify(structureRepo, times(2)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'une liste vide de structures")
	public void tester_getAllStructures_ListDONOTExists() {
		when(structureRepo.findAll()).thenReturn(Collections.emptyList());
		assertThrows(ElementNotFoundException.class, () -> sut.getAllStructures());
		verify(structureRepo, times(1)).findAll();
	}

	@Test
	@DisplayName("Test de récupération de la structure exixtante par son code")
	public void tester_getStructByCode_catExists() {
		when(structureRepo.findByCode(s1.getCode())).thenReturn(Optional.of(s1));
		Structure s = sut.getStructureByCode(s1.getCode());
		assertEquals(s, s1);
		assertDoesNotThrow(() -> sut.getStructureByCode(s1.getCode()));
		verify(structureRepo, times(2)).findByCode(s1.getCode());

	}


	@Test
	@DisplayName("Test de récupération de la structure inexixtante par son code")
	public void tester_getStructByCode_catDONOTExists() {
		when(structureRepo.findByCode(s1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.getStructureByCode(s1.getCode()));
		verify(structureRepo, times(1)).findByCode(s1.getCode());
	}

}
