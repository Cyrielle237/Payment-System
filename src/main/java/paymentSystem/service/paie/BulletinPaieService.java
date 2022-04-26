package paymentSystem.service.paie;

import java.util.List;

import paymentSystem.entity.paie.BulletinPaie;
import paymentSystem.entity.paie.RubriqueBulletinPaie;
import paymentSystem.entity.paie.RubriquePaie;
import paymentSystem.entity.ref.Banque;
import paymentSystem.entity.ref.Mois;
import paymentSystem.entity.rh.Personnel;
import paymentSystem.entity.rh.Rang;

public interface BulletinPaieService {

	BulletinPaie createBulletinPaie(BulletinPaie r);
	BulletinPaie createBulletinPaieByPersonnel(Personnel p);
	List<BulletinPaie> createBulletinPaieByPersonnelList(List<Personnel> p);

	BulletinPaie getBulletinPaieByCode(String code);

	List<BulletinPaie> getBulletinPaieByPersonnel(Personnel p);
	List<BulletinPaie> getBulletinPaieByAnnee(Integer annee);
	List<BulletinPaie> getBulletinPaieByMoisAnnee(Mois mois, Integer annee);
	List<BulletinPaie> getBulletinPaieByPersonnelMois(Personnel p, Mois mois);
	List<BulletinPaie> getBulletinPaieByPersonnelAnnee(Personnel p, Integer Annee);
	List<BulletinPaie> getBulletinPaieByPersonnelMoisAnnee(Personnel p, Mois mois, Integer annee);
	List<BulletinPaie> getAllBulletinPaies();

	void deleteBulletinPaieByCode(String code);
	void deleteBulletinPaieByPersonnel(Personnel p);
	void deleteBulletinPaieByAnnee(Integer annee);
	void deleteBulletinPaieByMoisAnnee(Mois mois, Integer annee);
	void deleteAllBulletinPaies();



	RubriqueBulletinPaie getSalaireDeBase(Personnel personnel, Mois mois, Integer annee) throws Exception;

	RubriqueBulletinPaie getMajorationInterne(Personnel personnel, Mois mois, Integer annee) throws Exception ;

	Double getIrpp(Double salaireBrutTaxable, Double revenusExceptionnels) throws Exception;

	Double getIrpp(Double salaireBrutTaxable) throws Exception;

	List<BulletinPaie> rechercherBulletins(Rang rang, String mois, int annee, Banque banque) throws Exception;

	List<BulletinPaie> rechercherBulletinsNouveaux(String mois, int annee, Banque banque, Rang... rangs) throws Exception ;

	List<BulletinPaie> rechercherBulletinsContractuels(String mois, int annee, Banque banque, Rang... rangs) throws Exception;

	List<BulletinPaie> rechercherBulletinsFonctionnaires(String mois, int annee, Banque banque, Rang... rangs) throws Exception;

	List<BulletinPaie> rechercherBulletinsParSousProg(String mois, Integer annee, Integer sousProg) throws Exception;

	List<BulletinPaie> rechercherBulletinsNouveauxProg(String mois, int annee, int numeroSp, Rang... rangs) throws Exception;

	List<BulletinPaie> rechercherBulletinsContractuelsProg(String mois, int annee, int numeroSp, Rang... rangs) throws Exception;

	List<BulletinPaie> rechercherBulletinsFonctionnairesProg(String mois, int annee, int numeroSp, Rang... rangs) throws Exception;

	List<BulletinPaie> rechercherBulletinsProg(Rang rang, String mois, int annee, int numeroSp) throws Exception;

	RubriqueBulletinPaie getRubrique13Mois(Personnel personnel, RubriquePaie rubriquePaie, Long montantBrut, Mois mois, Integer annee) throws Exception;

	RubriqueBulletinPaie getRubriqueIRNC(Personnel personnel, RubriquePaie rubriquePaie, Long montantBrut, Mois mois, Integer annee) throws Exception;






}
