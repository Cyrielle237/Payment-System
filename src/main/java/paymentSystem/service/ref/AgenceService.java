package paymentSystem.service.ref;

import java.util.List;

import paymentSystem.entity.ref.Agence;

public interface AgenceService {

	Agence createAgence(Agence a);

	Agence getAgenceByCode(String code);

	List<Agence> getAllAgences();

	void deleteAgence(String code);

	Agence updateAgence(Agence a);

}
