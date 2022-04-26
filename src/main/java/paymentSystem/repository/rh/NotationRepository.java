package paymentSystem.repository.rh;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.rh.Notation;

public interface NotationRepository extends CrudRepository<Notation, String> {

	Optional<Notation> findByCode(String code);

}
