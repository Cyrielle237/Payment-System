package paymentSystem.repository.rh;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.rh.Rang;

public interface RangRepository extends CrudRepository<Rang, String>{
	List<Rang> findByCodeOrDesignation(String code, String designation);
	Optional<Rang> findByCode(String code);
	List<Rang> findByDesignation(String designation);
	void deleteByCode(String code);

}
