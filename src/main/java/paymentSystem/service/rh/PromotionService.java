package paymentSystem.service.rh;

import java.util.List;

import paymentSystem.entity.rh.Promotion;

public interface PromotionService {
	Promotion createPromotion(Promotion p);

	Promotion getPromotionByCode(String code);

	List<Promotion> getAllPromotions();

}
