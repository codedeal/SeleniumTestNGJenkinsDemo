package com.crm.qa.base;

import org.openqa.selenium.WebElement;

public interface ICommonActions 
{
     void ClickOn(WebElement element);
     void SendData(WebElement element,String data);
     void WaitForWebelement();
     void DragAndDrop(WebElement source, WebElement destination);
}
