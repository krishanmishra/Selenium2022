package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtils;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtils elementUtils;

	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By address1 = By.id("input-address-1");
	private By city = By.id("input-city");
	private By pincode = By.id("input-postcode");
	private By country = By.id("input-country");
	private By state = By.id("input-zone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By privacyPolicy = By.name("agree");
	private By logout = By.linkText("Logout");
	private By registerlink = By.linkText("Register");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@type='radio' and @value='1']");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@type='radio' and @value='0']");
	private By successMessage = By.cssSelector("div#content h1");
	
	

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(this.driver);
	}

	public boolean registerUser(String firstname, String lastname, String email, String telephone, String address1,
			String city, String pincode,String country, String state, String password, String subscribe) throws InterruptedException {

		elementUtils.waitForElementVisible(this.firstname, Constants.DEFAULT_ELEMENT_TIME_OUT).sendKeys(firstname);

		elementUtils.doSendKeys(this.lastname, lastname);
		elementUtils.doSendKeys(this.email, email);
		elementUtils.doSendKeys(this.telephone, telephone);
		elementUtils.doSendKeys(this.address1, address1);
		elementUtils.doSendKeys(this.city, address1);
		elementUtils.doSendKeys(this.pincode, pincode);		
		elementUtils.selectDropDownByVisible(this.country, country);
		Thread.sleep(2000);
		elementUtils.selectDropDownByVisible(this.state, address1);
		elementUtils.doSendKeys(this.password, password);
		elementUtils.doSendKeys(this.confirmPassword, password);

		if (subscribe.equalsIgnoreCase("Yes")) {
			elementUtils.doClick(subscribeNo);
		} else {
			elementUtils.doClick(subscribeYes);
		}

		elementUtils.doClick(privacyPolicy);
		elementUtils.doClick(continueButton);		

		String successMessge = elementUtils
				.waitForElementVisible(this.successMessage, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
		Thread.sleep(5000);
		if (successMessge.contains(Constants.SUCCESS_REGISTRATION_MESSAGE)) {
			elementUtils.doClick(logout);
			elementUtils.doClick(registerlink);	
			return true;
		}else {
			elementUtils.doClick(privacyPolicy);
			elementUtils.doClick(continueButton);	
		}
		return false;
	}

}
