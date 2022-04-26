package paymentSystem.repository.security;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.security.Utilisateur;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, String> {
	List<Utilisateur> findByCodeOrLoginOrEtat(String code, String login, String etat);
	List<Utilisateur> findByLogin(String login);
	Optional<Utilisateur> findByCode(String code);
	void deleteByCode(String code);
}
