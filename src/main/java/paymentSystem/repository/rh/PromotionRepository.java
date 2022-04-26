package paymentSystem.repository.rh;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.rh.Fonction;
import paymentSystem.entity.rh.Personnel;
import paymentSystem.entity.rh.Promotion;

public interface PromotionRepository extends CrudRepository<Promotion, String>{

	List<Promotion> findByCodeOrPersonnelOrNouvelleFonctionOrAncienneFonction
	(String code, Personnel p, Fonction oldFonction, Fonction newFonction);

	Optional<Promotion>	findByCode(String code);


}
