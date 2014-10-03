package org.bgrimm.sync.service;

import org.bgrimm.sync.dao.AssetRepository;
import org.bgrimm.sync.dao.LocationRepository;
import org.bgrimm.sync.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("locationService")
@Transactional
public class LocationService {
	
	@Autowired
	private  LocationRepository locationRepopsitory;
	public void save(Location location) {
		locationRepopsitory.save(location);
		
	}

}
