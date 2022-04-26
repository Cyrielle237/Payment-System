package paymentSystem.repository.rh;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.rh.Conge;

public interface CongeRepository extends CrudRepository<Conge, String> {

	Optional<Conge> findByCode(String code);

}
