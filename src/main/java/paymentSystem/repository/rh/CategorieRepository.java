package paymentSystem.repository.rh;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.rh.Categorie;

public interface CategorieRepository extends CrudRepository<Categorie, String>{
	List<Categorie> findByCodeOrDesignationOrDescription(String code, String designation, String desc);

	Optional<Categorie> findByCode(String code);

	void deleteByCode(String code);

}
