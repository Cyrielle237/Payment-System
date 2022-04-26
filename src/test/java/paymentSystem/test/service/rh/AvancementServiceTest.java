package paymentSystem.test.service.rh;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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
import paymentSystem.entity.rh.Avancement;
import paymentSystem.entity.rh.Categorie;
import paymentSystem.entity.rh.Echelon;
import paymentSystem.entity.rh.Personnel;
import paymentSystem.repository.rh.AvancementRepository;
import paymentSystem.service.rh.AvancementServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AvancementServiceTest {
	private AvancementServiceImpl sut;

	@Mock
	private AvancementRepository avancementRepo;

	private Avancement a1;
	private List<Avancement> aList = new ArrayList<>();


	@BeforeEach
	public void init() {
		Categorie categorie = Categorie.builder().code("1369640790466").designation("11").build() ;
		Echelon echelon = Echelon.builder().code("1369641072669").description("A").build() ;
		Personnel personnel = Personnel.builder().code("1369640790466").matricule("092022").nom("MOUKOKO")
				.dateNaissance(LocalDate.of(1990,  10, 10)).datePriseService(LocalDate.of(2022, 1, 1)).categorie(categorie)
				.echelon(echelon).dureeContrat(2).build() ;
		sut = new AvancementServiceImpl(avancementRepo);
		a1 = Avancement.builder().code("1310001750153").personnel(personnel).build();
		aList.add(a1);
	}

	@AfterEach
	public void dropall() {
		a1 = null;
		aList = null;
	}


	@Test 
	@DisplayName("Test de ma creation d'un nouvel avancement")
	public void tester_creationAvancement() {
		when(avancementRepo.save(a1)).thenReturn(a1);
		Avancement s= sut.createAvancement(a1);
		assertThat(s).isSameAs(a1);
		verify(avancementRepo, times(1)).save(a1);
	}

	@Test
	@DisplayName("Test de récupération de la liste exixstante des avancements")
	public void tester_getAllAvancements_ListExists() {
		when(avancementRepo.findAll()).thenReturn(aList);
		List<Avancement> s = sut.getAllAvancements();
		assertDoesNotThrow(() -> sut.getAllAvancements());
		assertEquals(s, aList);
		verify(avancementRepo, times(2)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'une liste vide d'avancements")
	public void tester_getAllAvancements_ListDONOTExists() {
		when(avancementRepo.findAll()).thenReturn(Collections.emptyList());
		assertThrows(ElementNotFoundException.class, () -> sut.getAllAvancements());
		verify(avancementRepo, times(1)).findAll();
	}

	@Test
	@DisplayName("Test de récupération de l'avancement exixtant par son code")
	public void tester_getAffectByCode_catExists() {
		when(avancementRepo.findByCode(a1.getCode())).thenReturn(Optional.of(a1));
		Avancement s = sut.getAvancementByCode(a1.getCode());
		assertEquals(s, a1);
		assertDoesNotThrow(() -> sut.getAvancementByCode(a1.getCode()));
		verify(avancementRepo, times(2)).findByCode(a1.getCode());
	}


	@Test
	@DisplayName("Test de récupération de l'avancement inexixtant par son code")
	public void tester_getAvancementByCode_avctDONOTExists() {
		when(avancementRepo.findByCode(a1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.getAvancementByCode(a1.getCode()));
		verify(avancementRepo, times(1)).findByCode(a1.getCode());
	}


}
