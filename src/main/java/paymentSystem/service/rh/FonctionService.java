package paymentSystem.service.rh;

import java.util.List;

import paymentSystem.entity.rh.Fonction;

public interface FonctionService {
	Fonction createFonction(Fonction f);

	Fonction updateFonction(Fonction f);

	void deleteFonction(String code);

	Fonction getFonctionByCode(String code);

	List<Fonction> getAllFonctions();

}
