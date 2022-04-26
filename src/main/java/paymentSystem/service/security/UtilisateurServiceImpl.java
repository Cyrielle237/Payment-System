package paymentSystem.service.security;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.repository.security.UtilisateurRepository;
import paymentSystem.security.Utilisateur;

public class UtilisateurServiceImpl implements UtilisateurService {
	private UtilisateurRepository utilisateurRepo;

	public UtilisateurServiceImpl(UtilisateurRepository userRepo) {
		this.utilisateurRepo = userRepo;
	}


	@Override
	public Utilisateur createUtilisateur(Utilisateur u) {
		return utilisateurRepo.save(u);
	}



	@Override
	public Utilisateur updateUtilisateur(Utilisateur u) {
		Utilisateur user = utilisateurRepo.findByCode(u.getCode())
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("User not found", String.valueOf(u.getCode()))));
		user.setLogin(u.getLogin());
		user.setPersonnel(u.getPersonnel());
		user.setPassword(u.getPassword());
		return utilisateurRepo.save(user);
	}

	@Override
	public Utilisateur updateUtilisateurPwd(Utilisateur u) {
		Utilisateur user = utilisateurRepo.findByCode(u.getCode())
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("User not found", String.valueOf(u.getCode()))));
		user.setPassword(u.getPassword());
		return utilisateurRepo.save(user);

	}


	@Override
	public void deleteUtilisateur(String code) {
		Optional<Utilisateur> role = utilisateurRepo.findByCode(code);
		if(role.isPresent())
			utilisateurRepo.deleteByCode(code);
		else
			throw new ElementNotFoundException(String.format
					("User not found", String.valueOf(code)));
	}



	@Override
	public Utilisateur getUtilisateurByCode(String code) {
		Utilisateur user = utilisateurRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("User with ID %s not found",String.valueOf(code)))) ;
		return user;
	}


	@Override
	public List<Utilisateur> getAllUtilisateurs() {
		List<Utilisateur> listUsers = (List<Utilisateur>) utilisateurRepo.findAll();
		if(listUsers.size() >= 1) {
			return listUsers;
		}else
			throw new ElementNotFoundException();				
	}


}
