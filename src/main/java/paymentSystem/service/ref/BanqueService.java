package paymentSystem.service.ref;

import java.util.List;

import paymentSystem.entity.ref.Banque;

public interface BanqueService {
	Banque createBanque(Banque b);

	Banque updateBanque(Banque b);

	void deleteBanque(String code);

	Banque getBanqueByCode(String code);

	List<Banque> getAllBanques();

}
