package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.opencart.loggers.Log;

public class OptionsManager {

	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	private Properties prop;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			Log.info("Running Chrome Browser in Headless mode");
			co.addArguments("--headless");
		}

		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			Log.info("Running Chrome Browser in Incognito mode");
			co.addArguments("--incognito");
		}
		return co;
	}

	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			Log.info("Running Firefox Browser in Headless mode");
			fo.addArguments("--headless");
		}

		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			Log.info("Running Firefox Browser in Incognito mode");
			fo.addArguments("--incognito");
		}
		return fo;
	}

	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			Log.info("Running Edge Browser in Headless mode");
			eo.addArguments("--headless");
		}

		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			Log.info("Running Edge Browser in Private mode");
			eo.addArguments("--inprivate");
		}
		
		return eo;
	}
}
