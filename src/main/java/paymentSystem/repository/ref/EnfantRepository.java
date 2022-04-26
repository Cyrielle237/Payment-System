package paymentSystem.repository.ref;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.ref.Enfant;
import paymentSystem.entity.rh.Personnel;

public interface EnfantRepository extends CrudRepository<Enfant, String> {
	List<Enfant> findByCodeOrNomOrPersonnel(String code, String nom, Personnel personnel);


}
