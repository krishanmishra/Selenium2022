package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.FrameworkExceptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/*
	 * This method is used to initialize the webdriver on the basis of given browser
	 * name
	 * 
	 * @param browserName
	 * 
	 * @return driver
	 * 
	 */

	// public WebDriver init_driver(String browserName) {
	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser").trim();
		optionManager = new OptionsManager(prop);

		System.out.println("browser name is : " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver(optionManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver(optionManager.getFirfoxOptions());
			tlDriver.set(new FirefoxDriver(optionManager.getFirfoxOptions()));

		} else if (browserName.equalsIgnoreCase("safari")) {
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());

		} else {
			System.out.println("Please pass the right browser : " + browserName);
			throw new FrameworkExceptions("no browser option found");
		}
//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		// driver.get("https://demo.opencart.com/index.php?route=account/login");
//		driver.get(prop.getProperty("url"));
//		return driver;

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		// driver.get("https://demo.opencart.com/index.php?route=account/login");
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	/*
	 * get thread local copy of driver
	 * 
	 * @return
	 * 
	 * 
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	/*
	 * This method is used to initialize the properties
	 * 
	 */

	public Properties init_prop() {

		prop = new Properties();
		FileInputStream ip = null;

		// mvn clean install -Denv="qa"

		String envName = System.getProperty("env");
		System.out.println("Running tests on environment: " + envName);

		if (envName == null) {
			System.out.println("No env is given .....hence running it on QA environment");

			try {
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}

		} else {
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					System.out.println("running it on QA env");
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					System.out.println("running it on stage env");
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "demo":
					System.out.println("running it on demo env");
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				default:
					System.out.println("Please pass the right environment......" + envName);
					throw new FrameworkExceptions("no env is found exception....");
//					break;
				}
			} catch (Exception e) {

			}
		}

		try {
			prop.load(ip);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return prop;
	}

//		try {
//			 ip = new FileInputStream(".//src//test//resources//config//config.properties");
//			
//			prop.load(ip);
//
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//	}

	/*
	 * take screenshot
	 * 
	 */
	public String getScreenshot() {

		File srcfile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = "./" + "screenshot/" + System.currentTimeMillis() + ".png";

		File destination = new File(path);

		try {
			FileUtils.copyFile(srcfile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

}
