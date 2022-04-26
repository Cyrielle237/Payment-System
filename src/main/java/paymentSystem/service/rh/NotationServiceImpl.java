package paymentSystem.service.rh;

import java.util.List;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.entity.rh.Notation;
import paymentSystem.repository.rh.NotationRepository;

public class NotationServiceImpl implements NotationService {
	private NotationRepository notationRepo;

	public NotationServiceImpl(NotationRepository notationRepo) {
		this.notationRepo = notationRepo;
	}

	@Override
	public Notation createNotation(Notation n) {
		return notationRepo.save(n);
	}

	@Override
	public Notation getNotationByCode(String code) {
		Notation notation = notationRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Notation with ID %s not found",String.valueOf(code)))) ;
		return notation;
	}

	@Override
	public List<Notation> getAllNotations() {
		List<Notation> listNot = (List<Notation>) notationRepo.findAll();
		if(listNot.size() >= 1) {
			return listNot;
		}else
			throw new ElementNotFoundException();				
	}

}
