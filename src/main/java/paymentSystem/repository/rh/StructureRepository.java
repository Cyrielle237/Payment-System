package paymentSystem.repository.rh;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.rh.Structure;

public interface StructureRepository extends CrudRepository<Structure, String> {
	List<Structure> findByCodeOrDesignationOrSigleOrStructureParente(String code, String designation,
			String sigle, Structure structureParente);

	Optional<Structure> findByCode(String code);

	void deleteByCode(String code);


}
