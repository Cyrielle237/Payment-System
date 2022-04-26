package paymentSystem.service.ref;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import paymentSystem.entity.exceptions.ElementNotFoundException;
import paymentSystem.entity.ref.Region;
import paymentSystem.repository.ref.RegionRepository;

public class RegionServiceImpl implements RegionService {

	private RegionRepository regionRepo;

	public RegionServiceImpl(RegionRepository regionRepo) {
		this.regionRepo = regionRepo;
	}


	@Override
	public Region createRegion(Region r) {
		return regionRepo.save(r);
	}



	@Override
	public Region updateRegion(Region r){
		Region quart = regionRepo.findByCode(r.getCode())
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Region not found", String.valueOf(r.getCode()))));
		quart.setDesignation(r.getDesignation());
		return regionRepo.save(quart);
	}



	@Override
	public void deleteRegion(String code) {
		Optional<Region> region = regionRepo.findByCode(code);
		if(region.isPresent())
			regionRepo.deleteByCode(code);
		else
			throw new ElementNotFoundException(String.format
					("Region not found", String.valueOf(code)));
	}



	@Override
	public Region getRegionByCode(String code) {
		Region region = regionRepo.findByCode(code)
				.map(Function.identity())
				.orElseThrow(() -> new ElementNotFoundException(String.format
						("Region with ID %s not found",String.valueOf(code)))) ;
		return region;
	}


	@Override
	public List<Region> getAllRegions() {
		List<Region> listRegion = (List<Region>) regionRepo.findAll();
		if(listRegion.size() >= 1) {
			return listRegion;
		}else
			throw new ElementNotFoundException();				
	}
}
