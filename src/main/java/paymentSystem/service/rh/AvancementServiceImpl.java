package paymentSystem.service.rh;

import java.util.List;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.entity.rh.Avancement;
import paymentSystem.repository.rh.AvancementRepository;

public class AvancementServiceImpl implements AvancementService {

	private AvancementRepository avancementRepo;

	public AvancementServiceImpl(AvancementRepository avancementRepo) {
		this.avancementRepo = avancementRepo;
	}

	@Override
	public Avancement createAvancement(Avancement a) {
		return avancementRepo.save(a);
	}

	@Override
	public Avancement getAvancementByCode(String code) {
		Avancement avancement = avancementRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Avancement with ID %s not found",String.valueOf(code)))) ;
		return avancement;
	}

	@Override
	public List<Avancement> getAllAvancements() {
		List<Avancement> listAvmt = (List<Avancement>) avancementRepo.findAll();
		if(listAvmt.size() >= 1) {
			return listAvmt;
		}else
			throw new ElementNotFoundException();				
	}

}
