package org.bgrimm.sync.timer;

import javax.xml.datatype.XMLGregorianCalendar;

import org.bgrimm.sync.domain.Location;
import org.bgrimm.sync.service.LocationService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aeroscout.mobileview.api.dto.asset.AssetDTO;
import com.aeroscout.mobileview.api.dto.location.LocationDTO;
import com.aeroscout.mobileview.api.dto.location.PointDTO;
import com.aeroscout.mobileview.api.service.AssetAPIServicePortType;
import com.aeroscout.mobileview.api.service.LocatorAPIServicePortType;
import com.aeroscout.mobileview.api.service.SystemAPIServicePortType;
import com.aeroscout.mobileview.proxy.AeroScoutServiceLocator;

@Component
public class TimerTask {

	// @Autowired
	// private CityService cityService;
	//
	@Autowired
	private AeroscoutProperties prop;

	@Autowired
	private LocationService locationService;

	//
	// private AssetService assetService;

	// @Scheduled(fixedDelay = 10000)
	// public void timerRun() {
	// System.out.println("I am running...");
	// List<City> cityList = cityService.findAll();
	// System.out.println(cityList);
	// }

	@Scheduled(fixedDelay = 5000)
	public void test() {
		System.out.println("sdfasdfasdfasdfasdfasdfwww");
		// String host = "172.16.3.214";
		// int port = 803;
		// String user = "system";
		// String pass = "manager";

		String host = prop.getHost();
		int port = prop.getPort();
		String user = prop.getUser();
		String pass = prop.getPassword();

		AeroScoutServiceLocator aeroScoutServiceLocator = new AeroScoutServiceLocator(
				host, port, user, pass, false);

		SystemAPIServicePortType systemServicePort = aeroScoutServiceLocator
				.getSystemService();
		AssetAPIServicePortType assetServicePort = aeroScoutServiceLocator
				.getAssetService();
		AssetAPIServicePortType as = aeroScoutServiceLocator
				.getAssetAPIService();
		AssetDTO ste = as.findPopulatedAssetByTagNetworkId("000CCC54BF1E");
		AssetDTO asset = as.findPopulatedAssetById(1l);
		LocatorAPIServicePortType lap = aeroScoutServiceLocator
				.getLocatorAPIService();
		LocationDTO ld = lap.findAssetCurrentLocation(1l);
		PointDTO xy = ld.getPoint();

		// String ds = asset.getDescription();
		// AssetCriteriaDTO cri = new AssetCriteriaDTO();
		// QueryDTO query = new QueryDTO();
		// query.setPageNo(1);
		// query.setPageSize(20);
		// AssetQueryResultDTO result = as.findAssetsByCriteria(cri, query);
		// result.getAssets();
		System.out.println(xy.getY() + "," + xy.getX());
		System.out.println(ld.getDateCreated());

		Location location = new Location();
		location.setX(xy.getX());
		location.setY(xy.getY());
		location.setZ(xy.getZ());
		location.setId(ld.getId());

		// System.out.println(asset);
		XMLGregorianCalendar cal = ld.getDateCreated();
		DateTime dt = new DateTime(cal.getYear(), cal.getMonth(), cal.getDay(),
				cal.getHour(), cal.getMinute(), cal.getSecond(),
				cal.getMillisecond());
		location.setDate(dt.toDate());

		locationService.save(location);
	}

	// @Scheduled(fixedRate = 5000)
	// public void testProperties() {
	// String host = prop.getHost();
	// int port = prop.getPort();
	// String user = prop.getUser();
	// String pass = prop.getPassword();
	// System.out.println(prop);
	// }

	// @Scheduled(fixedDelay = 30000)
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

