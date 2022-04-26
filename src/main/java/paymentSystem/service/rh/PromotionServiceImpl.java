package paymentSystem.service.rh;

import java.util.List;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.entity.rh.Promotion;
import paymentSystem.repository.rh.PromotionRepository;

public class PromotionServiceImpl implements PromotionService {

	private PromotionRepository  promotionRepo;

	public PromotionServiceImpl(PromotionRepository  promotionRepo) {
		this. promotionRepo =  promotionRepo;
	}


	@Override
	public Promotion createPromotion(Promotion p){
		return promotionRepo.save(p);
	}

	@Override
	public Promotion getPromotionByCode(String code) {
		Promotion promotion = promotionRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Promotion with ID %s not found",String.valueOf(code)))) ;
		return promotion;
	}

	@Override
	public List<Promotion> getAllPromotions() {
		List<Promotion> listProm = (List<Promotion>) promotionRepo.findAll();
		if(listProm.size() >= 1) {
			return listProm;
		}else
			throw new ElementNotFoundException();				
	}


}
