package paymentSystem.service.ref;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.entity.ref.Banque;
import paymentSystem.repository.ref.BanqueRepository;

public class BanqueServiceImpl implements BanqueService {

	private BanqueRepository banqueRepo;

	public BanqueServiceImpl(BanqueRepository banqueRepo) {
		this.banqueRepo = banqueRepo;
	}


	@Override
	public Banque createBanque(Banque b) {
		return banqueRepo.save(b);
	}



	@Override
	public Banque updateBanque(Banque b){
		Banque bque = banqueRepo.findByCode(b.getCode())
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Banque not found", String.valueOf(b.getCode()))));
		bque.setDesignation(b.getDesignation());
		return banqueRepo.save(bque);
	}



	@Override
	public void deleteBanque(String code) {
		Optional<Banque> banque = banqueRepo.findByCode(code);
		if(banque.isPresent())
			banqueRepo.deleteByCode(code);
		else
			throw new ElementNotFoundException(String.format
					("Banque not found", String.valueOf(code)));
	}



	@Override
	public Banque getBanqueByCode(String code) {
		Banque banque = banqueRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Banque with ID %s not found",String.valueOf(code)))) ;
		return banque;
	}


	@Override
	public List<Banque> getAllBanques() {
		List<Banque> listBque = (List<Banque>) banqueRepo.findAll();
		if(listBque.size() >= 1) {
			return listBque;
		}else
			throw new ElementNotFoundException();				
	}

}
