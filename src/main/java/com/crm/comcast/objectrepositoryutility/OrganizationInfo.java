package com.crm.comcast.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfo {
	public OrganizationInfo(WebDriver driver) {             
		PageFactory.initElements(driver, this);
	}
	
	
	@FindAll ({@FindBy(xpath = "//span[@class= 'dvHeaderText']"), @FindBy(className = "dvHeaderText") ,@FindBy(xpath = "//span[contains(text(),'Organization Information')]") })
	  private WebElement successFullMsg;


	public WebElement getSuccessFullMsg() {
		return successFullMsg;
	}
	
	
}
