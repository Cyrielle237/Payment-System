package paymentSystem.service.ref;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.entity.ref.Ville;
import paymentSystem.repository.ref.VilleRepository;

public class VilleServiceImpl implements VilleService {

	private VilleRepository villeRepo;

	public VilleServiceImpl(VilleRepository villeRepo) {
		this.villeRepo = villeRepo;
	}


	@Override
	public Ville createVille(Ville v) {
		return villeRepo.save(v);
	}



	@Override
	public Ville updateVille(Ville v){
		Ville ville = villeRepo.findByCode(v.getCode())
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Ville not found", String.valueOf(v.getCode()))));
		ville.setDesignation(v.getDesignation());
		return villeRepo.save(ville);
	}



	@Override
	public void deleteVille(String code) {
		Optional<Ville> ville = villeRepo.findByCode(code);
		if(ville.isPresent())
			villeRepo.deleteByCode(code);
		else
			throw new ElementNotFoundException(String.format
					("Ville not found", String.valueOf(code)));
	}



	@Override
	public Ville getVilleByCode(String code) {
		Ville ville = villeRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Ville with ID %s not found",String.valueOf(code)))) ;
		return ville;
	}


	@Override
	public List<Ville> getAllVilles() {
		List<Ville> listVille = (List<Ville>) villeRepo.findAll();
		if(listVille.size() >= 1) {
			return listVille;
		}else
			throw new ElementNotFoundException();				
	}

}
