package paymentSystem.repository.ref;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.ref.Quartier;
import paymentSystem.entity.ref.Ville;

public interface QuartierRepository extends CrudRepository<Quartier, String> {
	List<Quartier> findByCodeOrDesignationOrVille(String code, String designation, Ville ville);

	Optional<Quartier> findByCode(String code);

	void deleteByCode(String code);


}
