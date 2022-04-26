package paymentSystem.service.rh;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.entity.rh.Categorie;
import paymentSystem.repository.rh.CategorieRepository;

public class CategorieServiceImpl  implements CategorieService {

	private CategorieRepository categorieRepo;

	public CategorieServiceImpl(CategorieRepository categorieRepo) {
		this.categorieRepo = categorieRepo;
	} 


	@Override
	public Categorie createCategorie(Categorie c) {
		return categorieRepo.save(c);
	}



	@Override
	public Categorie updateCategorie(Categorie c) {
		Categorie cat = categorieRepo.findByCode(c.getCode())
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Category not found", String.valueOf(c.getCode()))));
		cat.setDesignation(c.getDesignation());
		cat.setDescription(c.getDescription());
		return categorieRepo.save(cat);
	}



	@Override
	public void deleteCategorie(String code) {
		Optional<Categorie> categorie = categorieRepo.findByCode(code);
		if(categorie.isPresent())
			categorieRepo.deleteByCode(code);
		else
			throw new ElementNotFoundException(String.format
					("Category not found", String.valueOf(code)));
	}



	@Override
	public Categorie getCategorieByCode(String code) {
		Categorie categorie = categorieRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Category with ID %s not found",String.valueOf(code)))) ;
		return categorie;
	}


	@Override
	public List<Categorie> getAllCategories() {
		List<Categorie> listCat = (List<Categorie>) categorieRepo.findAll();
		if(listCat.size() >= 1) {
			return listCat;
		}else
			throw new ElementNotFoundException();				
	}

}


