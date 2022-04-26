package paymentSystem.repository.security;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.security.Action;


public interface ActionRepository extends CrudRepository<Action, String> {
	List<Action> findByCodeOrDesignation(String code, String designation);


}
