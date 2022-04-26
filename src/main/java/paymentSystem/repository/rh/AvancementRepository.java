package paymentSystem.repository.rh;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.rh.Avancement;
import paymentSystem.entity.rh.Categorie;
import paymentSystem.entity.rh.Echelon;
import paymentSystem.entity.rh.Personnel;

public interface AvancementRepository extends CrudRepository<Avancement, String> {
	//write the query
	List<Avancement> findByCodeOrPersonnelOrNouvelleCategorieOrAncienneCategorieOrNouvelEchelonOrAncienEchelon
	(String code, Personnel p, Categorie nCat, Categorie oldCat,Echelon newEch, Echelon oldEch );

	Optional<Avancement>findByCode(String code);

}
