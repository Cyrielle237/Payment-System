package paymentSystem.service.paie;

import java.util.List;

import paymentSystem.entity.paie.RubriquePaie;

public interface RubriquePaieService {

	RubriquePaie createRubriquePaie(RubriquePaie r);

	RubriquePaie getRubriquePaieByCode(String code);

	List<RubriquePaie> getAllRubriquePaies();

	void deleteRubriquePaie(String code);

	RubriquePaie updateRubriquePaie(RubriquePaie r);

}
