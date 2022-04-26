package paymentSystem.repository.rh;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.rh.Fonction;

public interface FonctionRepository extends CrudRepository<Fonction, String>{

	Optional<Fonction> findByCode(String code);

	List<Fonction> findByCodeOrDesignation(String code, String designation);

	void deleteByCode(String code);



}
