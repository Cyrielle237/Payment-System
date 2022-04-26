package paymentSystem.repository.rh;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.rh.Fonction;
import paymentSystem.entity.rh.Personnel;

public interface PersonnelRepository extends CrudRepository<Personnel, String> {

	Optional<Personnel> findByCode(String code);

	//	Optional<Personnel> findByAdresseEmail(String adresseEmail);
	//	List<Personnel> findByNom(String nom);
	//	List<Personnel> findByPrenom(String prenom);
	//	List<Personnel> findByFonction(Fonction fonction);
	//	List<Personnel> findByMatricule(String matricule);
	//	List<Personnel> findByNomAndPrenom(String nom, String prenom);
	//	List<Personnel> findByNumeroTelephone(String numeroTelephone);

	List<Personnel> findByCodeOrAdresseEmailOrNomOrPrenomOrFonctionOrMatriculeOrNumeroTelephone
	(String code, String mail, String nom, String prenom, Fonction f, String mat, String num);

	void deleteByCode(String code);

}
