package paymentSystem.repository.rh;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.rh.Echelon;

public interface EchelonRepository extends CrudRepository<Echelon, String>{
	List<Echelon> findByCodeOrDesignationOrDescription(String code, String designation, String desc);

	Optional<Echelon> findByCode(String code);

	void deleteByCode(String code);

}
