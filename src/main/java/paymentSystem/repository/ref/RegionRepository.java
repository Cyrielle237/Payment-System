package paymentSystem.repository.ref;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.ref.Region;

public interface RegionRepository extends CrudRepository<Region, String> {
	List<Region> findByCodeOrDesignation(String code, String designation);

	Optional<Region> findByCode(String code);

	void deleteByCode(String code);


}
