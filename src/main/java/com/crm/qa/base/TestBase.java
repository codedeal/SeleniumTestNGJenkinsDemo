package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.Reports;
import com.crm.qa.util.ScreenShot;
import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase implements ICommonActions
{

	protected static WebDriver driver;
	public static EventFiringWebDriver e_driver;
	public static Properties prop;
	public static WebEventListener eventListener;
	

	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/crm"
					+ "/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initialization(){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "/Users/Audi/Downloads/chromedriver");	
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", "/Users/naveenkhunteta/Documents/SeleniumServer/geckodriver");	
			driver = new FirefoxDriver(); 
		}
		ScreenShot screen = new ScreenShot(driver);
		
		e_driver= new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}

	public void ClickOn(WebElement element)
	{
		
		if(!element.isDisplayed())
			{
			  WaitForWebelement();
			}
		element.click();
		
		
	}

	public void SendData(WebElement element, String data)
	{
		if(!element.isDisplayed())
		{
		  WaitForWebelement();
		}
		element.sendKeys(data);
		
	}

	public void WaitForWebelement() 
	{
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
	}

	public void DragAndDrop(WebElement source, WebElement destination)
	{
		   Actions actionobj = new Actions(driver);        
		    actionobj.moveToElement(source);
		    actionobj.pause(10000); 
		    actionobj.click(destination);
		    actionobj.perform();
		
	}
	
	public String SelectMenuItem(WebElement element, String item)
	{
		new Actions(driver).moveToElement(element).perform();
		List<WebElement>items = driver.findElements(By.xpath(""));
		for(WebElement search : items)
		{
			if(search.getText().equals(item))
			{
				search.click();
				return search.getText();
			}
			  
			
		}
		return null;
	}
	
}
