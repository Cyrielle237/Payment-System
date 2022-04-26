package paymentSystem.service.rh;

import java.util.List;

import paymentSystem.entity.rh.Conge;

public interface CongeService {
	Conge createConge(Conge c);

	Conge getCongeByCode(String code);

	List<Conge> getAllConges();

}
