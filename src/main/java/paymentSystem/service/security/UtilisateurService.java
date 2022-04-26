package paymentSystem.service.security;

import java.util.List;

import paymentSystem.security.Utilisateur;

public interface UtilisateurService {

	Utilisateur createUtilisateur(Utilisateur u);

	Utilisateur getUtilisateurByCode(String code);

	List<Utilisateur> getAllUtilisateurs();

	void deleteUtilisateur(String code);

	Utilisateur updateUtilisateur(Utilisateur u);

	Utilisateur updateUtilisateurPwd(Utilisateur u);

}
