package com.crm.qa.util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class ScreenShot 
{
	static WebDriver driver;
	public ScreenShot(WebDriver driver)
	{
		this.driver= driver;
	}
	
	 public static String capture(String screenShotName) throws IOException
	 {
	     TakesScreenshot ts = (TakesScreenshot)driver;
	     File source = ts.getScreenshotAs(OutputType.FILE);
	     String dest = System.getProperty("user.dir")+"/test_output/"+screenShotName+".png";
	     File destination = new File(dest);
	     FileUtils.copyFile(source, destination);

	     return destination.getName();
	 }
	   
}
