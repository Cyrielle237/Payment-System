package paymentSystem.repository.paie;

import java.util.List;

import paymentSystem.entity.paie.BulletinPaie;
import paymentSystem.entity.paie.RubriqueBulletinPaie;
import paymentSystem.entity.paie.RubriquePaie;
import paymentSystem.entity.ref.Banque;
import paymentSystem.entity.ref.Mois;
import paymentSystem.entity.rh.Personnel;
import paymentSystem.entity.rh.Rang;

public interface BulletinPaieRepositoryCustom {
	public RubriqueBulletinPaie getRubrique13Mois(Personnel personnel,RubriquePaie rubriquePaie, Long montantBrut, Mois mois, Integer annee) throws Exception ;

	public RubriqueBulletinPaie getRubriqueIRNC(Personnel personnel, RubriquePaie rubriquePaie, Long montantBrut, Mois mois, Integer annee) throws Exception ;

	public RubriqueBulletinPaie getSalaireDeBase(Personnel personnel, Mois mois, Integer annee) throws Exception;

	public RubriqueBulletinPaie getMajorationInterne(Personnel personnel, Mois mois, Integer annee) throws Exception ;

	public Double getIrpp(Double salaireBrutTaxable, Double revenusExceptionnels) throws Exception;

	public Double getIrpp(Double salaireBrutTaxable) throws Exception;

	public List<BulletinPaie> rechercherBulletins(Rang rang, String mois, int annee, Banque banque) throws Exception ;

	public List<BulletinPaie> rechercherBulletinsContractuels(String mois, int annee, Banque banque, Rang... rangs) throws Exception ;

	public List<BulletinPaie> rechercherBulletinsFonctionnaires(String mois, int annee, Banque banque, Rang... rangs) throws Exception ;

	public List<BulletinPaie> rechercherBulletinsNouveaux(String mois, int annee, Banque banque, Rang... rangs) throws Exception ;

	public List<BulletinPaie> rechercherBulletinsParSousProg(String mois, Integer annee, Integer sousProg) throws Exception ;

	public List<BulletinPaie> rechercherBulletinsProg(Rang rang, String mois, int annee, int numeroSp) throws Exception ;

	public List<BulletinPaie> rechercherBulletinsFonctionnairesProg(String mois, int annee, int numeroSp, Rang... rangs) throws Exception ;

	public List<BulletinPaie> rechercherBulletinsContractuelsProg(String mois, int annee, int numeroSp, Rang... rangs) throws Exception ;

	public List<BulletinPaie> rechercherBulletinsNouveauxProg(String mois, int annee, int numeroSp, Rang... rangs) throws Exception ;


}
