package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtils;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtils elementUtils;

	// 1. Private By locators :

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By registerLink = By.linkText("Register");
	private By forgotPasswordLink = By.linkText("Forgotten Password");
	private By logoutSuccessMessage = By.cssSelector("div#content h1");

	// 2...create constuctor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		//or
		//elementUtils = new ElementUtils(this.driver);
	}

	// 3. page actions..
	@Step("Getting login page title of open cart")
	public String getLoginPageTitle() {
		// return driver.getTitle();
		return elementUtils.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_ELEMENT_TIME_OUT);
	}
	
	@Step("Getting login page title of open cart")
	public String getLoginPageUrl() {
		// return driver.getCurrentUrl();
		return elementUtils.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}

	@Step("user is able to login with username: {0} and password: {1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println(" login creds are: " + un + " :" + pwd);

//		driver.findElement(emailId).sendKeys(un);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
		elementUtils.waitForElementVisible(emailId,Constants.DEFAULT_ELEMENT_TIME_OUT).sendKeys(un);
		elementUtils.doSendKeys(password, pwd);
		elementUtils.doClick(loginBtn);
		return new AccountsPage(driver);

	}
	
	@Step("Getting login page title of open cart")
	public boolean isRegisterLinkExist() {
		//return driver.findElement(registerLink).isDisplayed();
		return elementUtils.doIsDisplayed(registerLink);
	}

	@Step("Getting login page title of open cart")
	public boolean isForgotPwdLinkExist() {
		//return driver.findElement(forgotPasswordLink).isDisplayed();
		return elementUtils.doIsDisplayed(forgotPasswordLink);
	}

	@Step("Fetching successfull message for logout")
	public String getLogoutSuccessMessage() {
		return elementUtils.waitForElementVisible(logoutSuccessMessage,Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
		
	}
	
	@Step("navigating to registration page")
	public RegisterPage registerUser() {
		elementUtils.waitForElementVisible(registerLink, Constants.DEFAULT_ELEMENT_TIME_OUT).click();
		return new RegisterPage(driver);
	}
}
