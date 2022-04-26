package paymentSystem.service.rh;

import java.util.List;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.entity.rh.Conge;
import paymentSystem.repository.rh.CongeRepository;

public class CongeServiceImpl  implements CongeService{
	private CongeRepository congeRepo;

	public CongeServiceImpl(CongeRepository congeRepo) {
		this.congeRepo = congeRepo;
	}

	@Override
	public Conge createConge(Conge c) {
		return congeRepo.save(c);
	}

	@Override
	public Conge getCongeByCode(String code) {
		Conge conge = congeRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Affectation with ID %s not found",String.valueOf(code)))) ;
		return conge;
	}

	@Override
	public List<Conge> getAllConges() {
		List<Conge> listConge = (List<Conge>) congeRepo.findAll();
		if(listConge.size() >= 1) {
			return listConge;
		}else
			throw new ElementNotFoundException();				
	}


}
