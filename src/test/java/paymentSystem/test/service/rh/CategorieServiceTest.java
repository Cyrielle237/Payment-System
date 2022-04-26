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
import paymentSystem.entity.rh.Categorie;
import paymentSystem.repository.rh.CategorieRepository;
import paymentSystem.service.rh.CategorieServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CategorieServiceTest {

	private CategorieServiceImpl sut;

	@Mock 
	private CategorieRepository categorieRepo;

	private Categorie cat1, cat2;
	private List<Categorie> catList = new  ArrayList<>();

	@BeforeEach
	public void init() {
		sut = new CategorieServiceImpl(categorieRepo);
		cat1 = Categorie.builder().code("1369640789186").designation("01").description("uno").build();
		cat2 = Categorie.builder().code("1369645789236").designation("02").build();
		catList.add(cat1);
		catList.add(cat2);
	}

	@AfterEach
	public void dropall() {
		cat1 = cat2 = null;
		catList = null;
	}


	@Test
	@DisplayName("Enregistrement d'une nouvelle categorie")
	public void tester_createCategorie() {
		when(categorieRepo.save(cat1)).thenReturn(cat1);
		Categorie c = sut.createCategorie(cat1);
		assertThat(c).isSameAs(cat1);
		verify(categorieRepo, times(1)).save(cat1);
	}


	@Test
	@DisplayName("Test desuppression d'une categorie existante")
	public void tester_deleteCategorie_categorieExists() {
		when(categorieRepo.findByCode(cat1.getCode())).thenReturn(Optional.of(cat1));
		doNothing().when(categorieRepo).deleteByCode(cat1.getCode());
		assertDoesNotThrow(() -> sut.deleteCategorie(cat1.getCode()));
		verify(categorieRepo, times(1)).deleteByCode(cat1.getCode());
	}


	@Test
	@DisplayName("Test de suppression d'une catégorie inexistante")
	public void tester_deleteCategorie_categorieDONOTExists() {
		when(categorieRepo.findByCode(cat1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.deleteCategorie(cat1.getCode()));
		verify(categorieRepo, times(1)).findByCode(cat1.getCode());
	}


	@Test
	@DisplayName("Test de mise à jour d'une catégorie existante")
	public void tester_updateCategorie_categorieExists() {
		when(categorieRepo.findByCode(cat1.getCode())).thenReturn(Optional.of(cat1));
		when(categorieRepo.save(cat1)).thenReturn(cat1);
		Categorie c = sut.updateCategorie(cat1);
		assertThat(c).isEqualTo(cat1);
		assertDoesNotThrow(() -> sut.updateCategorie(cat1));
		verify(categorieRepo, times(2)).findByCode(cat1.getCode());

	}

	@Test
	@DisplayName("Test de mise à jour d'une catégorie inexistante")
	public void tester_updateCategorie_categorieDONOTExists() {
		when(categorieRepo.findByCode(cat1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class , ()-> sut.updateCategorie(cat1));
		verify(categorieRepo, times(1)).findByCode(cat1.getCode());

	}

	@Test
	@DisplayName("Test de récupération de la liste exixstante de categories")
	public void tester_getAllCategories_ListExists() {
		when(categorieRepo.findAll()).thenReturn(catList);
		List<Categorie> cat = sut.getAllCategories();
		assertDoesNotThrow(() -> sut.getAllCategories());
		assertEquals(cat, catList);
		verify(categorieRepo, times(2)).findAll();
	}

	@Test
	@DisplayName("Test de récupération d'une liste vide de categories")
	public void tester_getAllCategories_ListDONOTExists() {
		when(categorieRepo.findAll()).thenReturn(Collections.emptyList());
		assertThrows(ElementNotFoundException.class, () -> sut.getAllCategories());
		verify(categorieRepo, times(1)).findAll();
	}

	@Test
	@DisplayName("Test de récupération de catégorie exixtante par son code")
	public void tester_getCatByCode_catExists() {
		when(categorieRepo.findByCode(cat1.getCode())).thenReturn(Optional.of(cat1));
		Categorie c = sut.getCategorieByCode(cat1.getCode());
		assertEquals(c, cat1);
		assertDoesNotThrow(() -> sut.getCategorieByCode(cat1.getCode()));
		verify(categorieRepo, times(2)).findByCode(cat1.getCode());

	}


	@Test
	@DisplayName("Test de récupération de catégorie inexixtante par son code")
	public void tester_getCatByCode_catDONOTExists() {
		when(categorieRepo.findByCode(cat1.getCode())).thenReturn(Optional.empty());
		assertThrows(ElementNotFoundException.class, () -> sut.getCategorieByCode(cat1.getCode()));
		verify(categorieRepo, times(1)).findByCode(cat1.getCode());
	}


}
