package com.qa.opencart.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HeadlessConcept {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();

		// headless: no browser launching
		// testing is happening behind the scence
		// faster than normal mode

		// incognito mode private mode

		ChromeOptions co = new ChromeOptions();
//		co.setHeadless(true);
//		co.addArguments("--headless");
		co.addArguments("--incognito");

		WebDriver driver = new ChromeDriver(co);
		
		driver.get("https://www.naveenautomationlabs.com/opencart/");
		System.out.println(driver.getTitle());
		
		driver.quit();

	}

}
