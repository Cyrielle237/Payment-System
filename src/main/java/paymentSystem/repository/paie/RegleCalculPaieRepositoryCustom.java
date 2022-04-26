package paymentSystem.repository.paie;

import java.util.List;

import paymentSystem.entity.paie.RegleCalculPaie;
import paymentSystem.entity.ref.Mois;
import paymentSystem.entity.rh.Personnel;

public interface RegleCalculPaieRepositoryCustom {
	public List<RegleCalculPaie> getRetenues( Personnel personnel, Mois mois, Integer annee) throws Exception;
	public List<RegleCalculPaie> getRevenusExceptionnels( Personnel personnel, Mois mois, Integer annee) throws Exception;
	public List<RegleCalculPaie> getAvantagesEnNature( Personnel personnel, Mois mois, Integer annee) throws Exception;
	public List<RegleCalculPaie> getPrimesIndemnites(Personnel personnel, Mois mois, Integer annee) throws Exception;
	public List<RegleCalculPaie> getReglesRubrique(Mois mois, Integer annee, String typeRubrique) throws Exception;

}
