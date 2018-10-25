package com.test.testcase;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class TestCaseExample {
	
	 WebDriver driver;
  @Test
  public void f() {
	  
	 
	  driver.navigate().to("google");
  }
  @BeforeMethod
  public void beforeMethod() {
	   driver= new ChromeDriver();
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
