package paymentSystem.service.rh;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.entity.rh.Echelon;
import paymentSystem.repository.rh.EchelonRepository;

public class EchelonServiceImpl implements EchelonService{
	private EchelonRepository echelonRepo;

	public EchelonServiceImpl(EchelonRepository echelonRepo) {
		this.echelonRepo = echelonRepo;
	}



	@Override 
	public Echelon createEchelon(Echelon e) {
		return echelonRepo.save(e);
	}


	@Override
	public Echelon updateEchelon(Echelon e) {
		Echelon ech = echelonRepo.findByCode(e.getCode())
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Echelon not found", String.valueOf(e.getCode()))));
		ech.setDesignation(e.getDesignation());
		ech.setDescription(e.getDescription());
		return echelonRepo.save(ech);
	}


	@Override
	public void deleteEchelon(String code) {
		Optional<Echelon> echelon = echelonRepo.findByCode(code);
		if(echelon.isPresent())
			echelonRepo.deleteByCode(code);
		else
			throw new ElementNotFoundException(String.format
					("Echelon not found", String.valueOf(code)));
	}



	@Override
	public Echelon getEchelonByCode(String code) {
		Echelon ech = echelonRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Echelon with ID %s not found",String.valueOf(code)))) ;
		return ech;
	}


	@Override
	public List<Echelon> getAllEchelons() {
		List<Echelon> listEch = (List<Echelon>) echelonRepo.findAll();
		if(listEch.size() >= 1) {
			return listEch;
		}else
			throw new ElementNotFoundException();				
	}

}
