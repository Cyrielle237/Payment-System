package paymentSystem.repository.paie;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.paie.VariablePaie;

public interface VariablePaieRepository extends CrudRepository<VariablePaie, String> {
	List<VariablePaie> findByCodeOrDesignationOrDescription(String code, String designation, String description);

}
