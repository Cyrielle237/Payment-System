
package paymentSystem.repository.ref;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.ref.Conjoint;
import paymentSystem.entity.rh.Personnel;

public interface ConjointRepository extends CrudRepository<Conjoint, String> {
	List<Conjoint> findByCodeOrNomOrPersonnel(String code, String nom, Personnel personnel);

}
