package paymentSystem.service.rh;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.entity.rh.Structure;
import paymentSystem.repository.rh.StructureRepository;

public class StructureServiceImpl implements StructureService {

	private StructureRepository structureRepo;

	public StructureServiceImpl(StructureRepository structureRepo) {
		this.structureRepo = structureRepo;
	}

	@Override
	public Structure createStructure(Structure s) {
		return structureRepo.save(s);
	}


	@Override
	public Structure updateStructure(Structure s) {
		Structure struct = structureRepo.findByCode(s.getCode())
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Structure not found", String.valueOf(s.getCode()))));
		struct.setDesignation(s.getDesignation());
		struct.setStructureParente(s.getStructureParente());
		return structureRepo.save(struct);
	}


	@Override
	public void deleteStructure(String code) {
		Optional<Structure> struct = structureRepo.findByCode(code);
		if(struct.isPresent())
			structureRepo.deleteByCode(code);
		else
			throw new ElementNotFoundException(String.format
					("Structure not found", String.valueOf(code)));
	}


	@Override
	public Structure getStructureByCode(String code) {
		Structure struct = structureRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Structure with ID %s not found",String.valueOf(code)))) ;
		return struct;
	}


	@Override
	public List<Structure> getAllStructures() {
		List<Structure> listStruct = (List<Structure>) structureRepo.findAll();
		if(listStruct.size() >= 1) {
			return listStruct;
		}else
			throw new ElementNotFoundException();				
	}

}
