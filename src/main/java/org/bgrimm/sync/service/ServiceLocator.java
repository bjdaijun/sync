package org.bgrimm.sync.service;

import org.bgrimm.sync.timer.AeroscoutProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeroscout.mobileview.proxy.AeroScoutServiceLocator;

@Service
public class ServiceLocator {

	@Autowired
	private AeroscoutProperties prop;

	private AeroScoutServiceLocator serviceLocator = null;

	public AeroScoutServiceLocator getLocator() {
		if (serviceLocator == null) {
			String host = prop.getHost();
			int port = prop.getPort();
			String user = prop.getUser();
			String pass = prop.getPassword();

			AeroScoutServiceLocator aeroScoutServiceLocator = new AeroScoutServiceLocator(
					host, port, user, pass, false);
			serviceLocator = aeroScoutServiceLocator;
		}
		return serviceLocator;
	}

}
