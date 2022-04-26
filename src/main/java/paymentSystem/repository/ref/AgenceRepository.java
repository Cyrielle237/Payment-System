package paymentSystem.repository.ref;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.ref.Agence;
import paymentSystem.entity.ref.Ville;

public interface AgenceRepository extends CrudRepository<Agence, String> {

	List<Agence> findByCodeOrDesignation(String code, String designation);

	List<Agence> findByCodeOrDesignationOrVille(String code, String designation, Ville ville);

	List<Agence> findByVilleOrDesignation(Ville ville, String designation);

	Optional<Agence> findByCode(String code);

	void deleteByCode(String code);

}
