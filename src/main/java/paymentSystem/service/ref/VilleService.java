package paymentSystem.service.ref;

import java.util.List;

import paymentSystem.entity.ref.Ville;

public interface VilleService {
	Ville createVille(Ville v);

	Ville updateVille(Ville v);

	void deleteVille(String code);

	Ville getVilleByCode(String code);

	List<Ville> getAllVilles();

}
