package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic -100:this topic is for login page of open cart application")
@Story("Login 101:Design the login for various feature")
public class LoginPageTest extends BaseTest {

	@Description("login page Title test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void loginPageTitleTest() {

		String title = loginPage.getLoginPageTitle();
		System.out.println("Page title is: " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("login page url test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void loginPageUrlTest() {
		String actualUrl = loginPage.getLoginPageUrl();
		System.out.println("Login Page url is: " + actualUrl);
		Assert.assertTrue(actualUrl.contains(Constants.LOGIN_PAGE_URL_FRACTION));
		
	}
	
	@Description("forgot password link test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void forgotPasswordLinkExist() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Description("registration link existed test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	public void registerLinkExist() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	
	@Description("usr is able to login to open cart application test...")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=5)
	public void loginTest() {
		Assert.assertTrue(loginPage
				.doLogin(prop.getProperty("username"), prop.getProperty("password"))
				   .isLogoutLinkExist());
	}
}
