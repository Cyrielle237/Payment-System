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
import paymentSystem.entity.ref.Agence;
import paymentSystem.entity.ref.Ville;
import paymentSystem.entity.rh.Affectation;
import paymentSystem.entity.rh.Categorie;
import paymentSystem.entity.rh.Echelon;
import paymentSystem.entity.rh.Fonction;
import paymentSystem.entity.rh.Personnel;
import paymentSystem.entity.rh.Structure;
import paymentSystem.repository.rh.AffectationRepository;
import paymentSystem.service.rh.AffectationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AffectationServiceTest {

	private AffectationServiceImpl sut;

	@Mock
	private AffectationRepository affectationRepo;

	private Affectation a1;
	private List<Affectation> aList = new ArrayList<>();


	@BeforeEach
	public void init() {
		Categorie categorie = Categorie.builder().code("1369640790466").designation("11").build() ;
		Echelon echelon = Echelon.builder().code("1369641072669").description("A").build() ;
		Fonction fonction = Fonction.builder().code("1369640790466").designation("Cadre").build();
		Ville ville = Ville.builder().code("137208229057").designation("Yaounde").build();
		Agence agence = Agence.builder().code("137208229057").designation("direction generale").ville(ville).build();
		Structure structure = Structure.builder().code("1310001750153").designation("CELLULE DE LA TRADUCTION ET D INTERPRETARIAT").sigle("CTI").build();
		Personnel personnel = Personnel.builder().code("1369640790466").matricule("092022").nom("MOUKOKO").prenom("Gilbert")
				.dateNaissance(LocalDate.of(1990,  10, 10)).datePriseService(LocalDate.of(2022, 1, 1)).categorie(categorie).echelon(echelon)
				.dureeContrat(2).fonction(fonction).build() ;
		sut = new AffectationServiceImpl(affectationRepo);
		a1 = Affectation.builder().code("1372082290577").personnel(personnel).ancienneAgence(agence)
				.ancienneFonction(fonction).ancienneStructure(structure).build();
		aList.add(a1);
	}

	@AfterEach
	public void dropall() {
		a1 = null;
		aList = null;
	}


	@Test 
	@DisplayName("Test de ma creation d'une nouvelle Affectation")
	public void tester_creationAffectation() {
		when(affectationRepo.save(a1)).thenReturn(a1);
		Affectation s= sut.createAffectation(a1);
		assertThat(s).isSameAs(a1);
		verify(affectationRepo, times(1)).save(a1);
	}

	@Test
	@DisplayName("Test de récupération de la liste exixstante des affectations")
	public void tester_getAllAffectations_ListExists() {
		when(affectationRepo.findAll()).thenReturn(aList);
		List<Affectation> s = sut.getAllAffectations();
		assertDoesNotThrow(() -> sut.getAllAffectations());
		assertEquals(s, aList);
		verify(affectationRepo, times(2)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'une liste vide d'affectations")
	public void tester_getAllAffectations_ListDONOTExists() {
		when(affectationRepo.findAll()).thenReturn(Collections.emptyList());
		assertThrows(ElementNotFoundException.class, () -> sut.getAllAffectations());
		verify(affectationRepo, times(1)).findAll();
	}

	@Test
	@DisplayName("Test de récupération de l'affectation exixtante par son code")
	public void tester_getAffectByCode_catExists() {
		when(affectationRepo.findByCode(a1.getCode())).thenReturn(Optional.of(a1));
		Affectation s = sut.getAffectationByCode(a1.getCode());
		assertEquals(s, a1);
		assertDoesNotThrow(() -> sut.getAffectationByCode(a1.getCode()));
		verify(affectationRepo, times(2)).findByCode(a1.getCode());
	}


	@Test
	@DisplayName("Test de récupération de l'affectation inexixtante par son code")
	public void tester_getAffectationByCode_affDONOTExists() {
		when(affectationRepo.findByCode(a1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.getAffectationByCode(a1.getCode()));
		verify(affectationRepo, times(1)).findByCode(a1.getCode());
	}

}
