package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtils;

public class ShopingCartPage {
	private WebDriver driver;
	private ElementUtils elementUtils;
	
	private By shopingHeader=By.cssSelector("");

	public ShopingCartPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(this.driver);
	}

	public String getShopingPageHeader() {

		return elementUtils.waitForElementVisible(shopingHeader,Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
	}

}
