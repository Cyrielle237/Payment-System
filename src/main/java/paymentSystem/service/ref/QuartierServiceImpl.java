package paymentSystem.service.ref;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.entity.ref.Quartier;
import paymentSystem.repository.ref.QuartierRepository;

public class QuartierServiceImpl implements QuartierService {

	private QuartierRepository quartierRepo;

	public QuartierServiceImpl(QuartierRepository quartierRepo) {
		this.quartierRepo = quartierRepo;
	}


	@Override
	public Quartier createQuartier(Quartier q) {
		return quartierRepo.save(q);
	}



	@Override
	public Quartier updateQuartier(Quartier q){
		Quartier quart = quartierRepo.findByCode(q.getCode())
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Quartier not found", String.valueOf(q.getCode()))));
		quart.setDesignation(q.getDesignation());
		return quartierRepo.save(quart);
	}



	@Override
	public void deleteQuartier(String code) {
		Optional<Quartier> quartier = quartierRepo.findByCode(code);
		if(quartier.isPresent())
			quartierRepo.deleteByCode(code);
		else
			throw new ElementNotFoundException(String.format
					("Quartier not found", String.valueOf(code)));
	}



	@Override
	public Quartier getQuartierByCode(String code) {
		Quartier quartier = quartierRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Quartier with ID %s not found",String.valueOf(code)))) ;
		return quartier;
	}


	@Override
	public List<Quartier> getAllQuartiers() {
		List<Quartier> listQtier = (List<Quartier>) quartierRepo.findAll();
		if(listQtier.size() >= 1) {
			return listQtier;
		}else
			throw new ElementNotFoundException();				
	}
}
