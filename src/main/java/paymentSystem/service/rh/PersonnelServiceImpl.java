package paymentSystem.service.rh;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.entity.rh.Personnel;
import paymentSystem.repository.rh.PersonnelRepository;

public class PersonnelServiceImpl implements PersonnelService {
	private PersonnelRepository personnelRepo;

	public PersonnelServiceImpl(PersonnelRepository personnelRepo) {
		this.personnelRepo = personnelRepo;
	}

	@Override
	public Personnel createPersonnel(Personnel p) {
		return personnelRepo.save(p);
	}

	@Override
	public Personnel updatePersonnel(Personnel p) {
		Personnel pers = personnelRepo.findByCode(p.getCode())
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Personnel not found", String.valueOf(p.getCode()))));
		pers.setCivilite(p.getCivilite());
		pers.setNom(p.getNom());
		pers.setPrenom(p.getPrenom());
		pers.setDateNaissance(p.getDateNaissance());
		pers.setNumeroTelephone(p.getNumeroTelephone());
		pers.setAdresseEmail(p.getAdresseEmail());
		pers.setSexe(p.getSexe());
		pers.setEtat(p.getEtat());
		pers.setSituationMatrimoniale(p.getSituationMatrimoniale());
		pers.setStatut(p.getStatut());
		pers.setDatePriseService(p.getDatePriseService());
		pers.setDateFinContrat(p.getDateFinContrat());
		pers.setMatricule(p.getMatricule());
		pers.setMatriculeCNPS(p.getMatriculeCNPS());
		pers.setMatriculeFP(p.getMatriculeFP());
		pers.setBanque(p.getBanque());
		pers.setCompteBancaire(p.getCompteBancaire());
		pers.setRegionOrigine(p.getRegionOrigine());
		pers.setCategorie(p.getCategorie());
		pers.setEchelon(p.getEchelon());
		pers.setStructure(p.getStructure());
		pers.setFonction(p.getFonction());
		pers.setAgence(p.getAgence());
		pers.setQuartier(p.getQuartier());
		pers.setRang(p.getRang());

		return personnelRepo.save(pers);
	}


	@Override
	public void deletePersonnel(String code) {
		Optional<Personnel> personnel = personnelRepo.findByCode(code);
		if(personnel.isPresent())
			personnelRepo.deleteByCode(code);
		else
			throw new ElementNotFoundException(String.format
					("Personnel not found", String.valueOf(code)));

	}


	@Override
	public Personnel getPersonnelByCode(String code) {
		Personnel personnel = personnelRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Personnel with ID %s not found",String.valueOf(code)))) ;
		return personnel;
	}


	@Override
	public List<Personnel> getAllPersonnels() {
		List<Personnel> listPers = (List<Personnel>) personnelRepo.findAll();
		if(listPers.size() >= 1) {
			return listPers;
		}else
			throw new ElementNotFoundException();				
	}

}
