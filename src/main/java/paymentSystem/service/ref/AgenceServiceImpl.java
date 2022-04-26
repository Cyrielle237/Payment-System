package paymentSystem.service.ref;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.entity.ref.Agence;
import paymentSystem.repository.ref.AgenceRepository;

public class AgenceServiceImpl implements AgenceService {

	private AgenceRepository agenceRepo;

	public AgenceServiceImpl(AgenceRepository agenceRepo) {
		this.agenceRepo = agenceRepo;
	}


	@Override
	public Agence createAgence(Agence a) {
		return agenceRepo.save(a);
	}



	@Override
	public Agence updateAgence(Agence a) {
		Agence agc = agenceRepo.findByCode(a.getCode())
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Agence not found", String.valueOf(a.getCode()))));
		agc.setDesignation(a.getDesignation());
		agc.setVille(a.getVille());
		return agenceRepo.save(agc);
	}



	@Override
	public void deleteAgence(String code) {
		Optional<Agence> agence = agenceRepo.findByCode(code);
		if(agence.isPresent())
			agenceRepo.deleteByCode(code);
		else
			throw new ElementNotFoundException(String.format
					("Agence not found", String.valueOf(code)));
	}



	@Override
	public Agence getAgenceByCode(String code) {
		Agence agence = agenceRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Agence with ID %s not found",String.valueOf(code)))) ;
		return agence;
	}


	@Override
	public List<Agence> getAllAgences() {
		List<Agence> listAgc = (List<Agence>) agenceRepo.findAll();
		if(listAgc.size() >= 1) {
			return listAgc;
		}else
			throw new ElementNotFoundException();				
	}

}
