package com.test.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GooglePageObject {
	
	@FindBy(how=How.ID,using="lst-ib")
	@CacheLookup
	public  static WebElement search_input;

}
