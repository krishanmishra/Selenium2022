package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ExcelUtils;

public class RegisterTest extends BaseTest {

	@BeforeClass
	public void registerSetup() {
		registerPage = loginPage.registerUser();
	}

	@DataProvider

	public Object[][] getRegisterDataSheet() {
		Object regData[][] = ExcelUtils.getData(Constants.REGISTER_DATA_SHEET);
		return regData;

	}
	
	public String getRandom() {
		Random random=new Random();
		String email="March22Automation"+random.nextInt(1000)+"@gmail.com";
		return email;
	}

	@Test(dataProvider = "getRegisterDataSheet")
	public void registrationUserTest(String firstname, String lastname, String telephone, String address1,
			String city, String pincode, String country, String state, String password, String subscribe)
			throws InterruptedException {

//		Assert.assertTrue(registerPage.registerUser("krisna", "kulka", "krishan1@gmail.com", "8907788990", "Delhi",
//				"Delhi", "12345", "India", "Delhi", "Pass@123", "Yes"));
		
		Assert.assertTrue(registerPage.registerUser(firstname, lastname,getRandom(),telephone,  address1,
				city, pincode, country, state, password,subscribe));
	}

}
