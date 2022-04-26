package paymentSystem.service.rh;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.entity.rh.Rang;
import paymentSystem.repository.rh.RangRepository;

public class RangServiceImpl implements RangService {
	private RangRepository  rangRepo;

	public RangServiceImpl(RangRepository  rangRepo) {
		this. rangRepo =  rangRepo;
	}

	@Override
	public Rang updateRang(Rang r) {
		Rang rng = rangRepo.findByCode(r.getCode())
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Rang not found", String.valueOf(r.getCode()))));
		rng.setDesignation(r.getDesignation());
		return rangRepo.save(rng);
	}

	@Override
	public void deleteRang(String code) {
		Optional<Rang> rng = rangRepo.findByCode(code);
		if(rng.isPresent())
			rangRepo.deleteByCode(code);
		else
			throw new ElementNotFoundException(String.format
					("Rang not found", String.valueOf(code)));

	}

	@Override
	public Rang getRangByCode(String code) {
		Rang rng = rangRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Rang with ID %s not found",String.valueOf(code)))) ;
		return rng;
	}

	@Override
	public List<Rang> getAllRangs() {
		List<Rang> listRng = (List<Rang>) rangRepo.findAll();
		if(listRng.size() >= 1) {
			return listRng;
		}else
			throw new ElementNotFoundException();	
	}

}
