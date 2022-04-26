package paymentSystem.repository.paie;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.paie.Paie;
import paymentSystem.entity.ref.Mois;

public interface PaieRepository extends CrudRepository<Paie, String> {
	List<Paie> findByCodeOrAnneeOrMoisOrEtat(String code, Integer annee, Mois mois, String etat); 

}
