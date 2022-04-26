package paymentSystem.service.ref;

import java.util.List;

import paymentSystem.entity.ref.Region;

public interface RegionService {

	Region createRegion(Region r);

	Region updateRegion(Region r);

	void deleteRegion(String code);

	Region getRegionByCode(String code);

	List<Region> getAllRegions();

}
