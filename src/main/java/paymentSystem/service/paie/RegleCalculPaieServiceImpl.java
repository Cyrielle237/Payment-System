package paymentSystem.service.paie;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.entity.paie.RegleCalculPaie;
import paymentSystem.entity.ref.Mois;
import paymentSystem.entity.rh.Personnel;
import paymentSystem.repository.paie.RegleCalculPaieRepository;

public class RegleCalculPaieServiceImpl implements RegleCalculPaieService {

	private RegleCalculPaieRepository reglesRepo;

	public RegleCalculPaieServiceImpl(RegleCalculPaieRepository regleRepo) {
		this.reglesRepo = regleRepo;
	}


	@Override
	public RegleCalculPaie createRegleCalculPaie(RegleCalculPaie r) {
		return reglesRepo.save(r);
	}



	@Override
	public RegleCalculPaie updateRegleCalculPaie(RegleCalculPaie regle) {
		RegleCalculPaie r = reglesRepo.findByCode(regle.getCode())
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Rules of pay not found", String.valueOf(regle.getCode()))));
		r.setRubriquePaie(regle.getRubriquePaie());
		r.setCategorie(regle.getCategorie());
		r.setRang(regle.getRang());
		r.setPersonnel(regle.getPersonnel());
		r.setEchelon(regle.getEchelon());
		r.setFonction(regle.getFonction());
		r.setStatutPersonnel(regle.getStatutPersonnel());
		r.setFormule(regle.getFormule());
		r.setFormuleBase(regle.getFormuleBase());
		r.setTauxApplique(regle.getTauxApplique());
		r.setMois(regle.getMois());
		r.setAnnee(regle.getAnnee());

		return reglesRepo.save(r);
	}



	@Override
	public void deleteRegleCalculPaie(String code) {
		Optional<RegleCalculPaie> categorie = reglesRepo.findByCode(code);
		if(categorie.isPresent())
			reglesRepo.deleteByCode(code);
		else
			throw new ElementNotFoundException(String.format
					("Rules of pay not found", String.valueOf(code)));
	}



	@Override
	public RegleCalculPaie getRegleCalculPaieByCode(String code) {
		RegleCalculPaie regle = reglesRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Rules of pay with ID %s not found",String.valueOf(code)))) ;
		return regle;
	}


	@Override
	public List<RegleCalculPaie> getAllRegleCalculPaies() {
		List<RegleCalculPaie> listRegles = (List<RegleCalculPaie>) reglesRepo.findAll();
		if(listRegles.size() >= 1) {
			return listRegles;
		}else
			throw new ElementNotFoundException();				
	}


	@Override
	public List<RegleCalculPaie> getReglesRubrique(Mois mois, Integer annee, String typeRubrique) throws Exception {
		List<RegleCalculPaie>  regles = reglesRepo.getReglesRubrique(mois, annee, typeRubrique);
		if(regles.size() >= 1 )
			return regles;
		else
			throw new ElementNotFoundException(String.format("Rules not found"));
	}


	@Override
	public List<RegleCalculPaie> getRetenues(Personnel personnel, Mois mois, Integer annee) throws Exception {
		List<RegleCalculPaie>  regles = reglesRepo.getRetenues(personnel, mois, annee);
		if(regles.size() >= 1 )
			return regles;
		else
			throw new ElementNotFoundException(String.format("Rules not found"));
	}


	@Override
	public List<RegleCalculPaie> getRevenusExceptionnels(Personnel personnel, Mois mois, Integer annee)
			throws Exception {
		List<RegleCalculPaie>  regles = reglesRepo.getRevenusExceptionnels(personnel, mois, annee);
		if(regles.size() >= 1 )
			return regles;
		else
			throw new ElementNotFoundException(String.format("Rules not found"));
	}


	@Override
	public List<RegleCalculPaie> getAvantagesEnNature(Personnel personnel, Mois mois, Integer annee) throws Exception {
		List<RegleCalculPaie>  regles = reglesRepo.getAvantagesEnNature(personnel, mois, annee);
		if(regles.size() >= 1 )
			return regles;
		else
			throw new ElementNotFoundException(String.format("Rules not found"));
	}


	@Override
	public List<RegleCalculPaie> getPrimesIndemnites(Personnel personnel, Mois mois, Integer annee) throws Exception {
		List<RegleCalculPaie>  regles = reglesRepo.getPrimesIndemnites(personnel, mois, annee);
		if(regles.size() >= 1 )
			return regles;
		else
			throw new ElementNotFoundException(String.format("Rules not found"));
	}


}
