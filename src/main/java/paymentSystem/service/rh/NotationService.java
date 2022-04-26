package paymentSystem.service.rh;

import java.util.List;

import paymentSystem.entity.rh.Notation;

public interface NotationService {
	Notation createNotation(Notation n);

	Notation getNotationByCode(String code);

	List<Notation> getAllNotations();


}
