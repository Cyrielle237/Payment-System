package paymentSystem.service.rh;

import java.util.List;

import paymentSystem.entity.rh.Personnel;

public interface PersonnelService {
	Personnel createPersonnel(Personnel p);

	Personnel updatePersonnel(Personnel p);

	void deletePersonnel(String code);

	Personnel getPersonnelByCode(String code);

	List<Personnel> getAllPersonnels();


}
