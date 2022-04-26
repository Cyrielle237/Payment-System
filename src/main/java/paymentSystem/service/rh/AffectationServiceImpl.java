package paymentSystem.service.rh;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.entity.rh.Affectation;
import paymentSystem.repository.rh.AffectationRepository;

@Transactional
@Service
public class AffectationServiceImpl implements AffectationService {

	private AffectationRepository affectationRepo;

	public AffectationServiceImpl(AffectationRepository affectationRepo) {
		this.affectationRepo = affectationRepo;
	}

	@Override
	public Affectation createAffectation(Affectation a) {
		return affectationRepo.save(a);
	}

	@Override
	public Affectation getAffectationByCode(String code) {
		Affectation affectation = affectationRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Affectation with ID %s not found",String.valueOf(code)))) ;
		return affectation;
	}

	@Override
	public List<Affectation> getAllAffectations() {
		List<Affectation> listAff = (List<Affectation>) affectationRepo.findAll();
		if(listAff.size() >= 1) {
			return listAff;
		}else
			throw new ElementNotFoundException();				
	}

}
