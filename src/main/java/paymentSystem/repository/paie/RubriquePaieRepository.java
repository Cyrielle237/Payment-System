package paymentSystem.repository.paie;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.paie.RubriquePaie;

public interface RubriquePaieRepository extends CrudRepository<RubriquePaie, String> {
	List<RubriquePaie> findByCodeOrDesignationOrType(String code, String designation, String type);

	Optional<RubriquePaie> findByCode(String code);

	void deleteByCode(String code);

}
