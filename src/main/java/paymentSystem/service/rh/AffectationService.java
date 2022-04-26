package paymentSystem.service.rh;

import java.util.List;

import paymentSystem.entity.rh.Affectation;

public interface AffectationService {

	Affectation createAffectation(Affectation a);

	Affectation getAffectationByCode(String code);

	List<Affectation> getAllAffectations();
}
