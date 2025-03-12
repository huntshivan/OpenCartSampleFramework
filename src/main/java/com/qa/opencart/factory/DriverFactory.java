package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.exceptions.MyException;
import com.qa.opencart.loggers.Log;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		String url = prop.getProperty("url");
		highlight = prop.getProperty("highlight");

		optionsManager = new OptionsManager(prop);

		//System.out.println("Browser Name Passed: " + browserName);
		Log.info("Browser Name Passed: " + browserName);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			//System.out.println("Running tests in Chrome browser");
			Log.info("Running tests in Chrome browser");
			// driver = new ChromeDriver();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;

		case "firefox":
			//System.out.println("Running tests in Firefox browser");
			Log.info("Running tests in Firefox browser");
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;

		case "edge":
			//System.out.println("Running tests in Edge browser");
			Log.info("Running tests in Edge browser");
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;

		default:
			//System.out.println("Please Pass The Right Browser");
			Log.error("Please Pass The Right Browser");
			throw new MyException("Incorrect Browser Name");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(url);

		return getDriver();
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProperties() {
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");

		try {
			if (envName == null) {
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
			} else {

				switch (envName.toLowerCase().trim()) {

				case "qa":
					Log.info("Running tests in QA env");
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;

				case "dev":
					Log.info("Running tests in DEV env");
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;

				case "uat":
					Log.info("Running tests in UAT env");
					ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
					break;

				case "prod":
					Log.info("Running tests in PROD env");
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
					
				default:
					//System.out.println("Please pass the correct environment");
					Log.error("Please pass the correct environment");
					throw new FrameworkException("INCORRECT ENV SPECIFIED" + envName);
				}
			}
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	/**
	 * take screenshot
	 */

	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp directory
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
}
