package paymentSystem.service.paie;

import java.util.List;

import paymentSystem.entity.paie.RegleCalculPaie;
import paymentSystem.entity.ref.Mois;
import paymentSystem.entity.rh.Personnel;



public interface RegleCalculPaieService {

	RegleCalculPaie createRegleCalculPaie(RegleCalculPaie r);

	RegleCalculPaie getRegleCalculPaieByCode(String code);

	List<RegleCalculPaie> getAllRegleCalculPaies();

	void deleteRegleCalculPaie(String code);

	RegleCalculPaie updateRegleCalculPaie(RegleCalculPaie r);


	List<RegleCalculPaie> getReglesRubrique( Mois mois, Integer annee, String typeRubrique) throws Exception;

	List<RegleCalculPaie> getRetenues(Personnel personnel, Mois mois, Integer annee) throws Exception ;

	List<RegleCalculPaie> getRevenusExceptionnels(Personnel personnel, Mois mois, Integer annee) throws Exception ;

	List<RegleCalculPaie> getAvantagesEnNature(Personnel personnel, Mois mois, Integer annee) throws Exception ;

	List<RegleCalculPaie> getPrimesIndemnites(Personnel personnel, Mois mois, Integer annee) throws Exception ;

}
