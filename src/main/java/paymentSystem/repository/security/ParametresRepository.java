package paymentSystem.repository.security;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.security.Parametres;


public interface ParametresRepository extends CrudRepository<Parametres, String> {
	List<Parametres> findByCode(String code);
}
