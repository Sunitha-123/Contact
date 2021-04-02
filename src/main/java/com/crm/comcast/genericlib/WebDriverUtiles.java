package com.crm.comcast.genericlib;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * 
 * @author Deepak
 *
 */
public class WebDriverUtiles {
   /**
    *  used to select the option from the dropdwon based on visible text
    * @param element
    * @param visibleText
    */
	public void select(WebElement element, String visibleText) {
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
		
	}
	/**
	 * used to select the option from the dropdwon based on position / index (index always start from 0)
	 * @param element
	 * @param index
	 */
	public void select(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
		
	}
	
	/**
	 *  used to wait for Element to be clickable in GUI  & check for the elemnet for every 500 milisecond , 
	 * @param driver
	 * @param elemnet
	 */
	public void waitforElementToBeClickable(WebDriver driver , WebElement element) {
		 WebDriverWait wait = new WebDriverWait(driver, IConstant.explicitlyWaitWiat);
		 wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	public void waitforElementToVisibale(WebDriver driver , WebElement element) {
		 WebDriverWait wait = new WebDriverWait(driver, IConstant.explicitlyWaitWiat);
		 wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElemnetContainsText(WebDriver driver , WebElement element , String text) {
		 WebDriverWait wait = new WebDriverWait(driver, IConstant.explicitlyWaitWiat);
		 wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}
	
	
	
	/**
	 * wait for DOM document to be load before finding any elemnet in GUI
	 * @param driver
	 */
	public void waitForHTMLDOM(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(IConstant.implicitlyWiat, TimeUnit.SECONDS);
		
	}
	
	/**
	 * used to wait for expected element in GUI
	 * @param element
	 * @throws Throwable
	 */
	public void waitforElement( WebElement element) throws Throwable {
		  int count = 0;
	        while(count < 40) {
			   try {
				   element.isDisplayed();
			     break;
			   }catch (Throwable e) {
				Thread.sleep(500);
				count++;
			   }
	        }
	}
	/**
	 * used to wait for expected element in GUI based on Xpath
	 * @param element
	 * @throws Throwable
	 */
	public void waitAndClick(WebDriver driver , String xpath) throws Throwable {
		  int count = 0;
	        while(count < 40) {
			   try {
				   driver.findElement(By.xpath(xpath)).click();
			     break;
			   }catch (Throwable e) {
				 Thread.sleep(500);
				 count++;
			   }
	        }
	}
	
	/**
	 * SWitch to Requried Window based on title Paremeter
	 * @param driver
	 * @param partailWindowTitle
	 */
	public void swicthToWindow(WebDriver driver , String partailWindowTitle) {
		  /*get all window session ID*/
		   Set<String> set = driver.getWindowHandles();
		   Iterator<String> it = set.iterator();
		    
		   while(it.hasNext()) {
			   String winID = it.next();
			   driver.switchTo().window(winID);
			   String actTitle = driver.getTitle();
			   if(actTitle.contains(partailWindowTitle)) {
				   System.out.println("swicthed to actual winow");
				   break;
			   }
		   }
	}
	/**
	 * used to Switch to Alert Popup & click on OK button
	 * @param driver
	 */
	public void alertOK(WebDriver driver) {
		
		driver.switchTo().alert().accept();
	}
	
	/**
	 * used to Switch to Alert Popup & click on cancel button
	 * @param driver
	 */
   public void alertCancel(WebDriver driver) {
		
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * used to take mouse cursor on expected element on the browser
	 * @param driver
	 * @param element
	 */
	public void moveToExpectedElemnet(WebDriver driver , WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	public void swictToFrame(WebDriver driver , int index) {
		driver.switchTo().frame(index);
	}
	
   public void swictToFrame(WebDriver driver , WebElement element) {
	   driver.switchTo().frame(element);
	}
   
   public void swictToFrame(WebDriver driver , String id, String name) {
	   WebElement fwb;
	     if(id!=null) {
	    	 driver.switchTo().frame(id);
	     }else {
	    	 driver.switchTo().frame(name);
	     }
	   
	}
   
	public String takeScreenshot(WebDriver driver,String methodName) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String screenShotpath=System.getProperty("user.dir")+"/Screenshot/"+methodName+JavaUtils.getDateAndTime()+".png";
		File dest=new File(screenShotpath);
		Files.copy(src, dest);
		return screenShotpath;
	}

}
