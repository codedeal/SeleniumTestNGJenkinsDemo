package com.crm.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.Logs;

public class LoginPage extends TestBase
{
	//Page Factory - OR:
		@FindBy(xpath="//*[@id=\"ap_email\"]")
		WebElement username;
		
		@FindBy(xpath="//*[@id=\"continue\"]")
		WebElement continuebutton;
		
		@FindBy(xpath="//*[@id=\"ap_password\"]")
		WebElement password;
		
		@FindBy(xpath="//*[@id=\"signInSubmit\"]")
		WebElement login;
	
		@FindBy(xpath="//*[@id=\"nav-link-accountList\"]")
		WebElement loginBtn;
		
		@FindBy(xpath="//button[contains(text(),'Sign Up')]")
		WebElement signUpBtn;
		
		@FindBy(xpath="//*[@id=\"nav-logo\"]/a[1]/span[1]")
		WebElement crmLogo;
		
		//Initializing the Page Objects:
		public LoginPage()
		{
			Logs.info("in Login Page");
			PageFactory.initElements(driver, this);
		}
		
		//Actions:
		public String validateLoginPageTitle(){
			return driver.getTitle();
		}
		
		public boolean validateCRMImage(){
			return crmLogo.isDisplayed();
		}
		
		public HomePage login(String un, String pwd){
			Logs.info("Login with username"+un+" and password "+pwd);
			loginBtn.click();
			username.sendKeys(un);
			continuebutton.click();
			password.sendKeys(pwd);
			login.click();
			
//			    	JavascriptExecutor js = (JavascriptExecutor)driver;
//			    	js.executeScript("arguments[0].click();", loginBtn);
			    	
			return new HomePage();
		}
		
}
