package paymentSystem.repository.ref;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.ref.Ville;

public interface VilleRepository extends CrudRepository<Ville, String> {
	List<Ville> findByCodeOrDesignation(String code, String designation);

	Optional<Ville> findByCode(String code);

	void deleteByCode(String code);


}
