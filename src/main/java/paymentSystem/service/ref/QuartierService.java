package paymentSystem.service.ref;

import java.util.List;

import paymentSystem.entity.ref.Quartier;

public interface QuartierService {
	Quartier createQuartier(Quartier q);

	Quartier updateQuartier(Quartier q);

	void deleteQuartier(String code);

	Quartier getQuartierByCode(String code);

	List<Quartier> getAllQuartiers();

}
