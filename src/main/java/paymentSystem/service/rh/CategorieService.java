package paymentSystem.service.rh;

import java.util.List;

import paymentSystem.entity.rh.Categorie;

public interface CategorieService {

	Categorie createCategorie(Categorie c);

	Categorie updateCategorie(Categorie c);

	void deleteCategorie(String code);

	Categorie getCategorieByCode(String code);

	List<Categorie> getAllCategories();
}
