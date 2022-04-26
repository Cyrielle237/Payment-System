package paymentSystem.service.rh;

import java.util.List;

import paymentSystem.entity.rh.Avancement;

public interface AvancementService {

	Avancement createAvancement(Avancement a);

	Avancement getAvancementByCode(String code);

	List<Avancement> getAllAvancements();

}
