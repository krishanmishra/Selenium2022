package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;

public class ShopingCartTest extends BaseTest {

	@BeforeClass
	public void shopingCartSetup() {
		shopingCartPage = productInfoPage.getShoppingCart();
	}

	@Test
	public void shoppingPageHeaderTest() {
		String title = shopingCartPage.getShopingPageHeader();
		System.out.println(title);
		Assert.assertEquals(title, Constants.SHOPING_PAGE_TITLE);
	}

}
