package paymentSystem.repository.paie;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.paie.RegleCalculPaie;
import paymentSystem.entity.paie.RubriquePaie;
import paymentSystem.entity.rh.Categorie;

public interface RegleCalculPaieRepository extends CrudRepository<RegleCalculPaie, String>, RegleCalculPaieRepositoryCustom {
	List<RegleCalculPaie> findByCodeOrRubriquePaieOrCategorie(String code, RubriquePaie r, Categorie categorie);

	Optional<RegleCalculPaie> findByCode(String code);

	void deleteByCode(String code); 
}
