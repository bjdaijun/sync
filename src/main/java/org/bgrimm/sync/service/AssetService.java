package org.bgrimm.sync.service;

import org.bgrimm.sync.dao.AssetRepository;
import org.bgrimm.sync.domain.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("assetService")
@Transactional
public class AssetService {
	@Autowired
	private  AssetRepository assetRepopsitory;

	public Asset save(Asset a) {
		
		return assetRepopsitory.save(a);
	}

	// @Autowired
	// public AssetService(AssetRepository assetRepopsitory) {
	// this.assetRepopsitory = assetRepopsitory;
	// }

}
