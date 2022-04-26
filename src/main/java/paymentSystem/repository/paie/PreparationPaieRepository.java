package paymentSystem.repository.paie;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.paie.Paie;
import paymentSystem.entity.paie.PreparationPaie;
import paymentSystem.entity.rh.Personnel;

public interface PreparationPaieRepository extends CrudRepository<PreparationPaie, String> {
	List<PreparationPaie> findByCodeOrPaieOrPersonnel(String code, Paie paie, Personnel personnel);

}
