package org.bgrimm.sync.timer;

import java.util.List;

import org.bgrimm.sync.domain.City;
import org.bgrimm.sync.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aeroscout.mobileview.api.service.AssetAPIServicePortType;
import com.aeroscout.mobileview.api.service.SystemAPIServicePortType;
import com.aeroscout.mobileview.proxy.AeroScoutServiceLocator;

@Component
public class TimerTask {

	@Autowired
	private CityService cityService;

	@Scheduled(fixedDelay = 10000)
	public void timerRun() {
		System.out.println("I am running...");
		List<City> cityList = cityService.findAll();
		System.out.println(cityList);
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
	}
}
