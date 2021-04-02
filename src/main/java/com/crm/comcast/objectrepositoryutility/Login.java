package com.crm.comcast.objectrepositoryutility;
/**
 * 
 * @author Deepak
 *
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {   /*Rule 1 */
	
	 public Login(WebDriver driver) {             //Rule:4
		PageFactory.initElements(driver, this);
	}
  

      @FindAll({ @FindBy(name = "user_name"),@FindBy(id = "userid") , })
      private WebElement userNameEdt;
      
      
      @FindBy(name = "user_password")
      private WebElement passwordEdt;
      
      @FindBy(id = "submitButton")
      private WebElement loginBtn;

	public WebElement getUserNameEdt() {    //Rule-3 : 
		return userNameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
      
	
	public void loginToAPP() {                //Rule -5
		userNameEdt.sendKeys("admin");
		passwordEdt.sendKeys("admin");
		loginBtn.click();
	}
	
	public void loginToAPP(String userName , String password) {
		userNameEdt.sendKeys(userName);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
      
}











