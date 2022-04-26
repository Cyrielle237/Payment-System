package paymentSystem.service.rh;

import java.util.List;

import paymentSystem.entity.rh.Rang;

public interface RangService {

	Rang updateRang(Rang r);

	void deleteRang(String code);

	Rang getRangByCode(String code);

	List<Rang> getAllRangs();

}
