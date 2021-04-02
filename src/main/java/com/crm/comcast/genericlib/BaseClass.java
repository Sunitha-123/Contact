package com.crm.comcast.genericlib;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.crm.comcast.objectrepositoryutility.Home;
import com.crm.comcast.objectrepositoryutility.Login;

public class BaseClass {
	public FileUtility fLib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public  JavaUtils jLib = new JavaUtils();
	public  WebDriverUtiles wLib = new WebDriverUtiles();
	public DataBaseUtilities dbLib = new DataBaseUtilities();
	public  WebDriver driver;
	public static WebDriver staticDriver;
	
	@BeforeSuite(groups = {"smokeTest","s"})
	public void configBS() {
		System.out.println("=====================DB & extend repost config=====================");
		//dbLib.connectToDB();
	}
	
	//@Parameters("Browser")
	@BeforeClass(groups = {"smokeTest","regressionTest"})
	public void configBC() throws Throwable {
		System.out.println("=============launch the browser================");
	       /*read common Data*/

			 String URL = fLib.getPropertyKeyValue("url");
		 String BROWSER = fLib.getPropertyKeyValue("browser");
			 /* launch the browser*/
			  if(BROWSER.equals("chrome")) {
				  driver = new ChromeDriver();
			  }else if(BROWSER.equals("firefox")) {
				  driver = new FirefoxDriver();			  
			  }else if(BROWSER.equals("ie")) {
				  driver = new InternetExplorerDriver();			  
			  }
			  staticDriver=driver;
			  wLib.waitForHTMLDOM(driver);
			  driver.get(URL);
	}
	
	@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void configBM() throws Throwable {
		System.out.println("==============login==========");
		 String USERNAME = fLib.getPropertyKeyValue("username");
		 String PASSWORD = fLib.getPropertyKeyValue("password");
		  
		 /*step 1 login to app*/
		  Login lp = new Login(driver);
		  lp.loginToAPP(USERNAME, PASSWORD);
	}
	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void configAM() {
		System.out.println("==============logout==========");
		 /*step 5:  logout*/
		Home hp = new Home(driver);
		  hp.logout();
	}
	
	@AfterClass(groups = {"smokeTest","regressionTest"})
	public void configAC() {
		System.out.println("=============close the browser================");
		 /*step 2 : close the browser*/ 
		  driver.close();

	}
	@AfterSuite(groups = {"smokeTest","regressionTest"})
	public void configAS() throws Throwable {
		System.out.println("=====================close DB & extend repost objects=====================");
		//dbLib.closeDb();
	}



}
