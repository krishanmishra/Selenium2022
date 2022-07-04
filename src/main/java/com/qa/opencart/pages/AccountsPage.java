package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtils;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtils elementUtils;

	private By headerTitle = By.cssSelector("div#logo a");
	private By logoutLink = By.linkText("Logout");
	private By sectionHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchButton = By.cssSelector("div#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
	}

	public String getAccountPageTitle() {
		return elementUtils.waitForTitleIs(Constants.ACCOUNT_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public String getAccountPageUrl() {
		return elementUtils.waitForUrlContains(Constants.ACCOUNT_PAGE_URLFRACTION, Constants.DEFAULT_TIME_OUT);
	}

	public String getAccHeader() {
		return elementUtils.doGetText(headerTitle);
	}

	public List<String> getAccountHeaderList() {
		List<WebElement> secList = elementUtils.getElements(sectionHeaders);

		List<String> secValList = new ArrayList<String>();

		for (WebElement e : secList) {
			String text = e.getText();
			secValList.add(text);
		}

		return secValList;

	}

	public boolean isLogoutLinkExist() {
		return elementUtils.waitForElementVisible(logoutLink, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}

	public LoginPage clickOnLogout() {
		if (isLogoutLinkExist()) {
			elementUtils.doClick(logoutLink);
		}

		return new LoginPage(driver);

	}

	public boolean isSearchExist() {
		return elementUtils.waitForElementVisible(search, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}

	
	public SearchResultPage doSearch(String searchKey) {
		if(isSearchExist()) {
			
			elementUtils.doSendKeys(search, searchKey);
			elementUtils.doClick(searchButton);
			return new SearchResultPage(driver);
			
		}
		return null;
	}
}
