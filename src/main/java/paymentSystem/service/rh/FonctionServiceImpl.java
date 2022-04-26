package paymentSystem.service.rh;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.entity.rh.Fonction;
import paymentSystem.repository.rh.FonctionRepository;

public class FonctionServiceImpl implements FonctionService{

	private FonctionRepository fonctionRepo;

	public FonctionServiceImpl(FonctionRepository fonctionRepo) {
		this.fonctionRepo = fonctionRepo;
	}


	@Override
	public Fonction createFonction(Fonction f) {
		return fonctionRepo.save(f);
	}


	@Override
	public Fonction updateFonction(Fonction f) {
		Fonction fct = fonctionRepo.findByCode(f.getCode())
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Fonction not found", String.valueOf(f.getCode()))));
		fct.setDesignation(f.getDesignation());
		return fonctionRepo.save(fct);
	}


	@Override
	public void deleteFonction(String code) {
		Optional<Fonction> fct = fonctionRepo.findByCode(code);
		if(fct.isPresent())
			fonctionRepo.deleteByCode(code);
		else
			throw new ElementNotFoundException(String.format
					("Fonction not found", String.valueOf(code)));
	}


	@Override
	public Fonction getFonctionByCode(String code) {
		Fonction fct = fonctionRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Fonction with ID %s not found",String.valueOf(code)))) ;
		return fct;
	}


	@Override
	public List<Fonction> getAllFonctions() {
		List<Fonction> listFct = (List<Fonction>) fonctionRepo.findAll();
		if(listFct.size() >= 1) {
			return listFct;
		}else
			throw new ElementNotFoundException();				
	}

}
