package paymentSystem.service.paie;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.entity.paie.RubriquePaie;
import paymentSystem.repository.paie.RubriquePaieRepository;

public class RubriquePaieServiceImpl implements RubriquePaieService {

	private RubriquePaieRepository rubriqueRepo;

	public RubriquePaieServiceImpl(RubriquePaieRepository rRepo) {
		this.rubriqueRepo = rRepo;
	}


	@Override
	public RubriquePaie createRubriquePaie(RubriquePaie r) {
		return rubriqueRepo.save(r);
	}



	@Override
	public RubriquePaie updateRubriquePaie(RubriquePaie rub) {
		RubriquePaie r = rubriqueRepo.findByCode(rub.getCode())
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Rubrique not found", String.valueOf(rub.getCode()))));
		r.setDescription(rub.getDescription());
		r.setTaxable(rub.getTaxable());
		r.setCotisable(rub.getCotisable());
		r.setTaux(rub.getTaux());
		r.setType(rub.getType());
		r.setPartSalariale(rub.getPartSalariale());
		r.setPartPatronale(rub.getPartPatronale());
		r.setVariablePaie(rub.getVariablePaie());
		r.setDesignation(rub.getDesignation());

		return rubriqueRepo.save(r);
	}



	@Override
	public void deleteRubriquePaie(String code) {
		Optional<RubriquePaie> categorie = rubriqueRepo.findByCode(code);
		if(categorie.isPresent())
			rubriqueRepo.deleteByCode(code);
		else
			throw new ElementNotFoundException(String.format
					("Rubrique of pay not found", String.valueOf(code)));
	}



	@Override
	public RubriquePaie getRubriquePaieByCode(String code) {
		RubriquePaie regle = rubriqueRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Rubrique of pay with ID %s not found",String.valueOf(code)))) ;
		return regle;
	}


	@Override
	public List<RubriquePaie> getAllRubriquePaies() {
		List<RubriquePaie> listRub = (List<RubriquePaie>) rubriqueRepo.findAll();
		if(listRub.size() >= 1) {
			return listRub;
		}else
			throw new ElementNotFoundException();				
	}


}
