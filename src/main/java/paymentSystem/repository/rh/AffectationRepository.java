package paymentSystem.repository.rh;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.rh.Affectation;
import paymentSystem.entity.rh.Personnel;

public interface AffectationRepository extends CrudRepository<Affectation, String> {

	Optional<Affectation> findByCode(String code);

	Affectation findByPersonnel(Personnel personnel);
}
