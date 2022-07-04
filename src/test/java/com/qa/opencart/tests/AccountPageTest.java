package com.qa.opencart.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("Epic -200:this topic is for Account page of open cart application")
@Story("Login 201:Account the login for various feature")
public class AccountPageTest extends BaseTest {

	Map<String, String> getProductInfo;

	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accPageTitleTest() {
		String title = accPage.getAccountPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}

	@Test
	public void accPageURLTest() {
		String accURL = accPage.getAccountPageUrl();
		System.out.println("Acc pageurl: " + accURL);
		Assert.assertTrue(accURL.contains(Constants.ACCOUNT_PAGE_URLFRACTION));
	}

	@Test
	public void accPageSectionsList() {
		List<String> accSecList = accPage.getAccountHeaderList();
		System.out.println("Account section list are: " + accSecList);
		Assert.assertEquals(accSecList, Constants.EXPECTED_ACCOUNT_SECTION_LIST);
	}

	@Test
	public void logoutLinkExist() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Test
	public void searchExist() {
		Assert.assertTrue(accPage.isSearchExist());
	}

	@DataProvider
	public Object[][] getSearchKey() {
		return new Object[][] { { "Macbook" }, { "iMac" }, { "Apple" }, { "Samsung" }

		};

	}

	@Test(dataProvider = "getSearchKey")
	public void SearchTest(String searchKey) {
		searchPage = accPage.doSearch(searchKey);
		Assert.assertTrue(searchPage.getSearchResultCount() > 0);
	}

	@DataProvider
	public Object[][] getProductName() {
		return new Object[][] { { "Macbook", "MacBook Pro" }, { "iMac", "iMac" }, { "Apple", "Apple Cinema 30\"" },
				{ "Samsung", "Samsung SyncMaster 941BW" }

		};

	}

	@Test(dataProvider = "getProductName", enabled=false)
	public void selectProductTest(String searchKey, String productName) {
		searchPage = accPage.doSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		String productheader = productInfoPage.getProductHeaderName();
		System.out.println("product header: " + productheader);
		Assert.assertEquals(productheader, productName);
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "Macbook", "MacBook Pro", 4 }, { "Samsung", "Samsung SyncMaster 941BW", 1 }

		};

	}

	@Test(dataProvider = "getProductData",enabled=false)
	public void selectProductImageTest(String searchKey, String productName, int productImageCount) {
		searchPage = accPage.doSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		int productImageCounts = productInfoPage.getProductImageCount();
		System.out.println("product image count: " + productImageCounts);
		Assert.assertEquals(productImageCounts, productImageCount);
	}

	@DataProvider
	public Object[][] getAddProductCart() {
		return new Object[][] { { "Macbook", "MacBook Pro", 4 }, { "Samsung", "Samsung SyncMaster 941BW", 2 }

		};

	}

	@Test(dataProvider = "getAddProductCart")
	public void addProductToCart(String searchKey, String productName, int Qty) {
		searchPage = accPage.doSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		String alertMessage = productInfoPage.addProductCart(Qty);
		System.out.println("product image count: " + alertMessage);
		Assert.assertTrue(alertMessage.contains(Constants.ALERT_ADD_TO_CART));
	}

//	public void getProductInfo() {
//		searchPage=accPage.doSearch("Macbook");
//		productInfoPage=searchPage.selectProduct("MacBook Pro");
//		getProductInfo=productInfoPage.getProductInformation();
//	}

	@Test
	public void productInfoTest() {

////		searchPage=accPage.doSearch("Macbook");
////		productInfoPage=searchPage.selectProduct("MacBook Pro");
////		getProductInfo=productInfoPage.getProductInformation();
//		
//		getProductInfo();
////		Assert.assertTrue(getProductInfo.get("name").equals("MacBook Pro"));
//		Assert.assertTrue(getProductInfo.get("Brand").equals("Apple"));
////		Assert.assertTrue(getProductInfo.get("Price").equals("$2,000.00"));
//		Assert.assertTrue(getProductInfo.get("Availability").equals("In Stock"));
		searchPage = accPage.doSearch("Macbook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		getProductInfo = productInfoPage.getProductInformation();

//		softAssert.assertTrue(getProductInfo.get("name").equals("MacBook Pro11"));
//		softAssert.assertTrue(getProductInfo.get("Brand").equals("Apple"));
//		softAssert.assertTrue(getProductInfo.get("Price").equals("$2,000.00"));
//		softAssert.assertTrue(getProductInfo.get("Availability").equals("In Stock"));
//		softAssert.assertAll();
		
		softAssert.assertEquals(getProductInfo.get("name"),"MacBook Pro");
		softAssert.assertEquals(getProductInfo.get("Brand"),"Apple");
		softAssert.assertEquals(getProductInfo.get("Price"),"$2,000.00");
		softAssert.assertEquals(getProductInfo.get("Availability"),"In Stock");
		softAssert.assertAll();
	}

//	@Test
//	public void productNameTest() {
//		getProductInfo();
//		Assert.assertTrue(getProductInfo.get("name").equals("MacBook Pro"));
//	}
//	
//	@Test
//	public void productPriceTest() {
//		getProductInfo();
//		Assert.assertTrue(getProductInfo.get("Price").equals("$2,000.00"));
//	}
//	

//	@Test
//	public void logoutTest() {
//		Assert.assertEquals(accPage.clickOnLogout().getLogoutSuccessMessage(), Constants.LOGOUT_SUCCESS_MESSG);
//	}

}
