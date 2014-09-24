package org.bgrimm.sync.timer;

import java.util.List;

import org.bgrimm.sync.domain.City;
import org.bgrimm.sync.service.AssetService;
import org.bgrimm.sync.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aeroscout.mobileview.api.dto.QueryDTO;
import com.aeroscout.mobileview.api.dto.asset.AssetCriteriaDTO;
import com.aeroscout.mobileview.api.dto.asset.AssetDTO;
import com.aeroscout.mobileview.api.dto.asset.AssetQueryResultDTO;
import com.aeroscout.mobileview.api.service.AssetAPIServicePortType;
import com.aeroscout.mobileview.api.service.SystemAPIServicePortType;
import com.aeroscout.mobileview.proxy.AeroScoutServiceLocator;

@Component
public class TimerTask {

	@Autowired
	private CityService cityService;

	@Autowired
	private AeroscoutProperties prop;

	private AssetService assetService;

	@Scheduled(fixedDelay = 10000)
	public void timerRun() {
		System.out.println("I am running...");
		List<City> cityList = cityService.findAll();
		System.out.println(cityList);
	}

	@Scheduled(fixedDelay = 5000)
	public void test() {
		String host = "172.16.3.214";
		int port = 803;
		String user = "system";
		String pass = "manager";
		AeroScoutServiceLocator aeroScoutServiceLocator = new AeroScoutServiceLocator(
				host, port, user, pass, false);

		SystemAPIServicePortType systemServicePort = aeroScoutServiceLocator
				.getSystemService();
		AssetAPIServicePortType assetServicePort = aeroScoutServiceLocator
				.getAssetService();
		AssetAPIServicePortType as = aeroScoutServiceLocator
				.getAssetAPIService();
		AssetDTO asset = as.findPopulatedAssetById(1l);
		AssetCriteriaDTO cri = new AssetCriteriaDTO();
		QueryDTO query = new QueryDTO();
		query.setPageNo(1);
		query.setPageSize(20);
		AssetQueryResultDTO result = as.findAssetsByCriteria(cri, query);
		result.getAssets();
		System.out.println(result);

		System.out.println(asset);

	}

	@Scheduled(fixedRate = 5000)
	public void testProperties() {
		String host = prop.getHost();
		int port = prop.getPort();
		String user = prop.getUser();
		String pass = prop.getPassword();
		System.out.println(prop);
	}

	@Scheduled(fixedDelay = 30000)
	public void dataSync() {
		String host = "";
		int port = 802;
		String user = "system";
		String pass = "123456";
		AeroScoutServiceLocator aeroScoutServiceLocator = new AeroScoutServiceLocator(
				host, port, user, pass, false);

		SystemAPIServicePortType systemServicePort = aeroScoutServiceLocator
				.getSystemService();
		AssetAPIServicePortType assetServicePort = aeroScoutServiceLocator
				.getAssetService();

		// A ,
		//
		// B
		// i

		// City c=new City("beijing","aaa");
		// cityService.save(c);

		//
		// new B
		//
		// b.setA(A.get()
		//
		// )

	}
}
