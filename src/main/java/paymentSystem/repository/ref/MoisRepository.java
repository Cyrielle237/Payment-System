package paymentSystem.repository.ref;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.ref.Mois;

public interface MoisRepository extends CrudRepository<Mois, String> {
	List<Mois> findByCodeOrLabel(String code, String label);


}
