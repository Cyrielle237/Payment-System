package paymentSystem.service.paie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import paymentSystem.entity.exceptions.BackendException;
import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.entity.paie.BulletinPaie;
import paymentSystem.entity.paie.RubriqueBulletinPaie;
import paymentSystem.entity.paie.RubriquePaie;
import paymentSystem.entity.ref.Banque;
import paymentSystem.entity.ref.Mois;
import paymentSystem.entity.rh.Personnel;
import paymentSystem.entity.rh.Rang;
import paymentSystem.repository.paie.BulletinPaieRepository;

public class BulletinPaieServiceImpl implements BulletinPaieService {

	private BulletinPaieRepository bulRepo;

	public BulletinPaieServiceImpl(BulletinPaieRepository bulRepo) {
		this.bulRepo = bulRepo;
	}

	@Override
	public BulletinPaie createBulletinPaie(BulletinPaie r) {
		return bulRepo.save(r); 
	}

	@Override
	public BulletinPaie createBulletinPaieByPersonnel(Personnel p) {


		return null;
	}

	@Override
	public List<BulletinPaie> createBulletinPaieByPersonnelList(List<Personnel> p){
		List<BulletinPaie> result = new ArrayList<>();
		BulletinPaie b;
		for (Personnel personnel : p) {
			b = createBulletinPaieByPersonnel(personnel);
			result.add(b);
		}
		return (List<BulletinPaie>) bulRepo.saveAll(result);
	}


	@Override
	public BulletinPaie getBulletinPaieByCode(String code) {
		BulletinPaie bulletin = bulRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Rules of pay with ID %s not found",String.valueOf(code)))) ;

		return bulletin;
	}


	@Override
	public List<BulletinPaie> getBulletinPaieByPersonnel(Personnel p) {

		List<BulletinPaie> bulletin = bulRepo.findByPersonnel(p);
		if(bulletin.size() >= 1 )
			return bulletin;
		else
			throw new ElementNotFoundException(String.format
					("Bulletin of personnel not found", String.valueOf(p.getNom())));
	}


	@Override
	public List<BulletinPaie> getBulletinPaieByAnnee(Integer annee) {
		List<BulletinPaie> bulletin = bulRepo.findByAnnee(annee);
		if(bulletin.size() >= 1 )
			return bulletin;
		else
			throw new ElementNotFoundException(String.format
					("Bulletin of year not found", String.valueOf(annee)));
	}


	@Override
	public List<BulletinPaie> getBulletinPaieByMoisAnnee(Mois mois, Integer annee) {
		List<BulletinPaie> bulletin = bulRepo.findByMoisAndAnnee(mois, annee);
		if(bulletin.size() >= 1 )
			return bulletin;
		else
			throw new ElementNotFoundException(String.format
					("Bulletin of year and month not found", String.valueOf(annee + mois.getLabel())));
	}


	@Override
	public List<BulletinPaie> getBulletinPaieByPersonnelMois(Personnel p, Mois mois) {
		List<BulletinPaie> bulletin = bulRepo.findByPersonnelAndMois(p, mois);
		if(bulletin.size() >= 1 )
			return bulletin;
		else
			throw new ElementNotFoundException(String.format
					("Bulletin of personnel  for the given month not found", String.valueOf(p.getNom() + mois.getLabel())));
	}

	@Override
	public List<BulletinPaie> getBulletinPaieByPersonnelAnnee(Personnel p, Integer annee) {
		List<BulletinPaie> bulletin = bulRepo.findByPersonnelAndAnnee(p, annee);
		if(bulletin.size() >= 1 )
			return bulletin;
		else
			throw new ElementNotFoundException(String.format
					("Bulletin of personnel for the given year not found", String.valueOf(p.getNom() + annee )));
	}

	@Override
	public List<BulletinPaie> getBulletinPaieByPersonnelMoisAnnee(Personnel p, Mois mois, Integer annee) {
		List<BulletinPaie> bulletin = bulRepo.findByPersonnelAndMoisAndAnnee(p, mois, annee);
		if(bulletin.size() >= 1)
			return bulletin;
		else
			throw new ElementNotFoundException(String.format
					("Bulletin of the employee not found", String.valueOf(p.getNom())));
	}


	@Override
	public List<BulletinPaie> getAllBulletinPaies() {
		List<BulletinPaie> bulletin = (List<BulletinPaie>) bulRepo.findAll();
		if(bulletin.size() >= 1 )
			return bulletin;
		else
			throw new ElementNotFoundException(String.format("Bulletin of personnel not found"));
	}

	@Override
	public void deleteBulletinPaieByCode(String code) {
		Optional<BulletinPaie> bulletin = bulRepo.findByCode(code);
		if(bulletin.isPresent())
			bulRepo.deleteByCode(code);
		else
			throw new ElementNotFoundException(String.format
					("Bulletin of pay not found", String.valueOf(code)));
	}

	@Override
	public void deleteBulletinPaieByPersonnel(Personnel p) {
		List<BulletinPaie> bulletin = bulRepo.findByPersonnel(p);
		if(bulletin.size() >= 1 )
			bulRepo.deleteByPersonnel(p);
		else
			throw new ElementNotFoundException(String.format
					("Bulletin of employee not found", String.valueOf(p.getCode())));
	}

	@Override
	public void deleteBulletinPaieByAnnee(Integer annee) {
		List<BulletinPaie> bulletin = bulRepo.findByAnnee(annee);
		if(bulletin.size() >= 1 )
			bulRepo.deleteByAnnee(annee);
		else
			throw new ElementNotFoundException(String.format
					("Bulletin of year not found", String.valueOf(annee)));
	}

	@Override
	public void deleteBulletinPaieByMoisAnnee(Mois mois, Integer annee) {
		List<BulletinPaie> bulletin = bulRepo.findByMoisAndAnnee(mois, annee);
		if(bulletin.size() >= 1 )
			bulRepo.deleteByMoisAndAnnee(mois, annee);
		else
			throw new ElementNotFoundException(String.format
					("Bulletin of month not found", String.valueOf(mois.getLabel() + annee)));

	}

	@Override
	public void deleteAllBulletinPaies() {
		List<BulletinPaie> bulletin = (List<BulletinPaie>) bulRepo.findAll();
		if(bulletin.size() >= 1 )
			bulRepo.deleteAll();
		else
			throw new ElementNotFoundException(String.format
					("Bulletin of employee not found"));
	}


	@Override
	public RubriqueBulletinPaie getSalaireDeBase(Personnel personnel, Mois mois, Integer annee) throws Exception {
		RubriqueBulletinPaie r = bulRepo.getSalaireDeBase(personnel, mois, annee);
		if(r.getCode() != null)
			return r;
		else
			throw new BackendException(String.format("Salaire de base ne peut etre calculée"));			

	}


	@Override
	public RubriqueBulletinPaie getMajorationInterne(Personnel personnel, Mois mois, Integer annee) throws Exception {
		RubriqueBulletinPaie r = bulRepo.getMajorationInterne(personnel, mois, annee);
		if(r.getCode() != null)
			return r;
		else
			throw new BackendException(String.format("Majoration interne ne peut etre calculée"));			

	}


	@Override
	public Double getIrpp(Double salaireBrutTaxable, Double revenusExceptionnels) throws Exception {
		Double irpp = bulRepo.getIrpp(salaireBrutTaxable, revenusExceptionnels);
		if(irpp != null)
			return irpp;
		else
			throw new BackendException(String.format("L'irpp ne peut etre calculée"));			
	}


	@Override
	public Double getIrpp(Double salaireBrutTaxable) throws Exception {
		Double irpp = bulRepo.getIrpp(salaireBrutTaxable);
		if(irpp != null)
			return irpp;
		else
			throw new BackendException(String.format("L'irpp ne peut etre calculée"));			
	} 


	@Override
	public List<BulletinPaie> rechercherBulletins(Rang rang, String mois, int annee, Banque banque) throws Exception {
		List<BulletinPaie> bulletin = bulRepo.rechercherBulletins(rang, mois, annee, banque);
		if(bulletin.size() >= 1 )
			return bulletin;
		else
			throw new ElementNotFoundException(String.format("Bulletin not found"));
	}


	@Override
	public List<BulletinPaie> rechercherBulletinsNouveaux(String mois, int annee, Banque banque, Rang... rangs)
			throws Exception {
		List<BulletinPaie> bulletin = bulRepo.rechercherBulletinsNouveaux(mois, annee, banque, rangs);
		if(bulletin.size() >= 1 )
			return bulletin;
		else
			throw new ElementNotFoundException(String.format("Bulletin not found"));
	}


	@Override
	public List<BulletinPaie> rechercherBulletinsContractuels(String mois, int annee, Banque banque, Rang... rangs)
			throws Exception {
		List<BulletinPaie> bulletin = bulRepo.rechercherBulletinsContractuels(mois, annee, banque, rangs);
		if(bulletin.size() >= 1 )
			return bulletin;
		else
			throw new ElementNotFoundException(String.format("Bulletin not found"));
	}


	@Override
	public List<BulletinPaie> rechercherBulletinsFonctionnaires(String mois, int annee, Banque banque, Rang... rangs)
			throws Exception {
		List<BulletinPaie> bulletin = bulRepo.rechercherBulletinsFonctionnaires(mois, annee, banque, rangs);
		if(bulletin.size() >= 1 )
			return bulletin;
		else
			throw new ElementNotFoundException(String.format("Bulletin not found"));
	}


	@Override
	public List<BulletinPaie> rechercherBulletinsParSousProg(String mois, Integer annee, Integer sousProg)
			throws Exception {
		List<BulletinPaie> bulletin = bulRepo.rechercherBulletinsParSousProg(mois, annee, sousProg);
		if(bulletin.size() >= 1 )
			return bulletin;
		else
			throw new ElementNotFoundException(String.format("Bulletin not found"));
	}


	@Override
	public List<BulletinPaie> rechercherBulletinsNouveauxProg(String mois, int annee, int numeroSp, Rang... rangs)
			throws Exception {
		List<BulletinPaie> bulletin = bulRepo.rechercherBulletinsNouveauxProg(mois, annee, numeroSp, rangs);
		if(bulletin.size() >= 1 )
			return bulletin;
		else
			throw new ElementNotFoundException(String.format("Bulletin not found"));
	}


	@Override
	public List<BulletinPaie> rechercherBulletinsContractuelsProg(String mois, int annee, int numeroSp, Rang... rangs)
			throws Exception {
		List<BulletinPaie> bulletin = bulRepo.rechercherBulletinsContractuelsProg(mois, annee, numeroSp, rangs);
		if(bulletin.size() >= 1 )
			return bulletin;
		else
			throw new ElementNotFoundException(String.format("Bulletin not found"));
	}


	@Override
	public List<BulletinPaie> rechercherBulletinsFonctionnairesProg(String mois, int annee, int numeroSp, Rang... rangs)
			throws Exception {
		List<BulletinPaie> bulletin = bulRepo.rechercherBulletinsFonctionnairesProg(mois, annee, numeroSp, rangs);
		if(bulletin.size() >= 1)
			return bulletin;
		else
			throw new ElementNotFoundException(String.format("Bulletin not found"));
	}


	@Override
	public List<BulletinPaie> rechercherBulletinsProg(Rang rang, String mois, int annee, int numeroSp)
			throws Exception {
		List<BulletinPaie> bulletin = bulRepo.rechercherBulletinsProg(rang, mois, annee, numeroSp);
		if(bulletin.size() >= 1 )
			return bulletin;
		else
			throw new ElementNotFoundException(String.format("Bulletin not found"));
	}


	@Override
	public RubriqueBulletinPaie getRubrique13Mois(Personnel personnel, RubriquePaie rubriquePaie, Long montantBrut,
			Mois mois, Integer annee) throws Exception {
		RubriqueBulletinPaie rubrique = bulRepo.getRubrique13Mois(personnel, rubriquePaie, montantBrut, mois, annee);
		if(rubrique.getCode() != null)
			return rubrique;
		else
			throw new ElementNotFoundException(String.format("rubrique de bulletin non trouvée"));
	}


	@Override
	public RubriqueBulletinPaie getRubriqueIRNC(Personnel personnel, RubriquePaie rubriquePaie, Long montantBrut,
			Mois mois, Integer annee) throws Exception {
		RubriqueBulletinPaie rubrique = bulRepo.getRubriqueIRNC(personnel, rubriquePaie, montantBrut, mois, annee);
		if(rubrique.getCode() != null)
			return rubrique;
		else
			throw new ElementNotFoundException(String.format("rubrique de bulletin non trouvée"));
	}































}
