package com.test.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GooglePageObject {
	
	@FindBy(how=How.ID,using="lst-ib")
	@CacheLookup
	public  static WebElement search_input;
	
	@FindBy(how=How.XPATH,using="//form[@id='tsf']//input[@value='Recherche Google']")
	@CacheLookup
	public static WebElement search_btn;
	
	
	@FindBy(how=How.ID,using="rso")
	@CacheLookup
	public static WebElement result_container;

	@FindBy(how=How.XPATH,using="//div[@class='srg']/div")
	@CacheLookup
	public static WebElement result_entry;
}
