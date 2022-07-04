package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtils;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtils elementUtils;
	
	private By searchResults=By.cssSelector("div.product-layout.product-grid");
	private By productNameLink;
	

	public SearchResultPage(WebDriver driver) {
		this.driver=driver;
		elementUtils = new ElementUtils(driver);
	}

	public int getSearchResultCount() {
		
		return elementUtils.waitForElementsVisible(searchResults, Constants.DEFAULT_ELEMENT_TIME_OUT).size();
	}

	public ProductInfoPage selectProduct(String productName) {
		productNameLink=By.linkText(productName);
		elementUtils.doClick(productNameLink);
		return new ProductInfoPage(driver);
	}
	
	
}
