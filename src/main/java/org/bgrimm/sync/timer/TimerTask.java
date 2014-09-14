package org.bgrimm.sync.timer;

import java.util.List;

import org.bgrimm.sync.domain.City;
import org.bgrimm.sync.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

}
