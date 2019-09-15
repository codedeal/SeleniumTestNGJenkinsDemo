package com.crm.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.Logs;

import junit.framework.Assert;

public class LoginPageTest extends TestBase
{
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest()
	{
		super(); // To Call the Super Class Constructor so to initialize properties of Super Class
	}
	
	@BeforeMethod
	public void SetUp()
	{
		initialization();
		loginPage = new LoginPage();

	}
	
	//Test cases
	

	@Test(priority=1)
	public void loginPageTitleTest(){
		Logs.info("Test the Tilte of Login Page");
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
	}
	
	@Test(priority=2)
	public void crmLogoImageTest(){
		boolean flag = loginPage.validateCRMImage();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void loginTest() throws InterruptedException{
		Logs.info("Login and move to home page");
		Thread.sleep(3000);
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void tearDown()
	{
		Logs.info("Exit the TC");
		driver.quit();
	}


}
