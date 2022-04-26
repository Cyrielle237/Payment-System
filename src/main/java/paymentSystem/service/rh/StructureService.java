package paymentSystem.service.rh;

import java.util.List;

import paymentSystem.entity.rh.Structure;

public interface StructureService {
	Structure createStructure(Structure s);

	Structure updateStructure(Structure s);

	void deleteStructure(String code);

	Structure getStructureByCode(String code);

	List<Structure> getAllStructures();

}
