package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.Logs;

public class HomePage extends TestBase
{
	@FindBy(xpath = "//*[@id=\"nav-link-accountList\"]/span[1]")
	@CacheLookup
	WebElement userNameLabel;

	@FindBy(xpath="//*[@id=\"glow-ingress-line2\"]")
	WebElement deliveryAddress;
	
	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath = "//a[contains(text(),'New Contact')]")
	WebElement newContactLink;
	

	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	WebElement dealsLink;

	@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
    
	@FindBy(xpath="//*[@id=\"twotabsearchtextbox\"]")
	WebElement searcBox;
	
	@FindBy(xpath="//*[@id=\"nav-search\"]/form/div[2]/div/input")
	WebElement submitSearch;
	// Initializing the Page Objects:
	public HomePage() {
		Logs.info("Home Page got intialized");
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String verifyHomePageTitle(){
		return driver.getTitle();
	}
	
	
	public boolean verifyCorrectUserName(){
		return userNameLabel.isDisplayed();
	}
	public String DeliveryCity()
	{
		return deliveryAddress.getText();
	}
	
	public ContactsPage clickOnContactsLink(){
		contactsLink.click();
		return new ContactsPage();
	}
	
	public DealsPage clickOnDealsLink(){
		dealsLink.click();
		return new DealsPage();
	}
	
	public TasksPage clickOnTasksLink(){
		tasksLink.click();
		return new TasksPage();
	}
	
	public void clickOnNewContactLink(){
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		newContactLink.click();	
	}
	
  public void SendKeyInSearch(String item)
  {
	  searcBox.sendKeys(item);
	  submitSearch.click();
	  
  }
}
