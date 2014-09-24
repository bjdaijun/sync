package org.bgrimm.sync.timer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "aeroscout", ignoreUnknownFields = false)
@Component
public class AeroscoutProperties {

	private String host;
	private int port;
	private String user;
	private String password;

	@Override
	public String toString() {
		return "AeroscoutProperties [host=" + host + ", port=" + port
				+ ", user=" + user + ", password=" + password + "]";
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
