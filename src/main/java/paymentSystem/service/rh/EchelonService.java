package paymentSystem.service.rh;

import java.util.List;

import paymentSystem.entity.rh.Echelon;

public interface EchelonService {
	Echelon createEchelon(Echelon e);

	Echelon updateEchelon(Echelon e);

	void deleteEchelon(String code);

	Echelon getEchelonByCode(String code);

	List<Echelon> getAllEchelons();

}
