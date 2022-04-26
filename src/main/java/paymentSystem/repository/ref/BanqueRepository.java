package paymentSystem.repository.ref;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.ref.Banque;

public interface BanqueRepository extends CrudRepository<Banque, String> {
	List<Banque> findByCodeOrDesignation(String code, String designation);

	Optional<Banque> findByCode(String code);

	void deleteByCode(String code);

}
