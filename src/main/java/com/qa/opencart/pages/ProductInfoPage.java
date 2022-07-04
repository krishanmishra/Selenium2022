package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtils;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtils elementUtils;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productQty = By.cssSelector("input#input-quantity");
	private By addToCartButton = By.cssSelector("button#button-cart");
	private By alertText = By.cssSelector("div.alert");
	private By shoppingCartAlertText = By.linkText("shopping cart");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	
	HashMap<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(this.driver);
	}

	public String getProductHeaderName() {
		return elementUtils.waitForElementVisible(productHeader, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
	}

	public int getProductImageCount() {
		return elementUtils.waitForElementsVisible(productImages, Constants.DEFAULT_ELEMENT_TIME_OUT).size();
	}

	public String addProductCart(int Qty) {

		elementUtils.doSendKeys(productQty, String.valueOf(Qty));
		elementUtils.doClick(addToCartButton);
		return elementUtils.waitForElementVisible(alertText, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();

	}

	public ShopingCartPage getShoppingCart() {
		elementUtils.doClick(shoppingCartAlertText);
		return new ShopingCartPage(driver);
	}

	public HashMap<String, String> getProductInformation() {

		productInfoMap = new HashMap<String, String>();
		productInfoMap.put("name", getProductHeaderName());
		// meta data
		getProductMetaData();
		// price
		getProductPriceData();
		
		productInfoMap.forEach((k,v) -> System.out.println(k+":"+v));
		
		return productInfoMap;

	}

	private void getProductMetaData() {
		List<WebElement> metaDataList = elementUtils.getElements(productMetaData);
		System.out.println("total product meta data: " + metaDataList.size());

		for (WebElement e : metaDataList) {

			String meta[] = e.getText().split(":");
			String metakey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metakey, metaValue);
		}

	}

	private void getProductPriceData() {
		List<WebElement> priceList = elementUtils.getElements(productPriceData);

		String price = priceList.get(0).getText().trim();
		String exTaxPrice = priceList.get(1).getText().trim();

		productInfoMap.put("Price", price);
		productInfoMap.put("ExTaxPrice", exTaxPrice);
	}

}
