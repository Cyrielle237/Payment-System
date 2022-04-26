package paymentSystem.repository.paie;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import paymentSystem.entity.paie.BulletinPaie;
import paymentSystem.entity.ref.Mois;
import paymentSystem.entity.rh.Personnel;

public interface BulletinPaieRepository extends CrudRepository<BulletinPaie, String>, BulletinPaieRepositoryCustom {

	Optional<BulletinPaie> findByCode(String code);

	List<BulletinPaie> findByMoisAndAnnee(Mois mois, Integer annee);
	List<BulletinPaie> findByAnnee(Integer annee);
	List<BulletinPaie> findByPersonnel(Personnel personnel);
	List<BulletinPaie> findByMois(Mois mois);
	List<BulletinPaie> findByPersonnelAndAnnee(Personnel p, Integer annee);
	List<BulletinPaie> findByPersonnelAndMois(Personnel p, Mois mois);
	List<BulletinPaie> findByPersonnelAndMoisAndAnnee(Personnel p,Mois mois, Integer annee);
	List<BulletinPaie> findByCodeOrPersonnelOrMoisOrAnnee(String code, Personnel p,Mois mois, Integer annee);

	void deleteByCode(String code);
	void deleteByPersonnel(Personnel p);
	void deleteByAnnee(Integer annee);
	void deleteByMoisAndAnnee(Mois mois, Integer annee);

}
