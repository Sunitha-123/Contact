package com.crm.autodesk.contacttest;

import org.apache.tools.ant.taskdefs.optional.ejb.WeblogicDeploymentTool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.comcast.genericlib.BaseClass;
import com.crm.comcast.genericlib.ExcelUtility;
import com.crm.comcast.genericlib.FileUtility;
import com.crm.comcast.genericlib.JavaUtils;
import com.crm.comcast.genericlib.WebDriverUtiles;
import com.crm.comcast.objectrepositoryutility.ContactInfoPage;
import com.crm.comcast.objectrepositoryutility.Contacts;
import com.crm.comcast.objectrepositoryutility.CreateNewConatct;
import com.crm.comcast.objectrepositoryutility.CreateNewOrganization;
import com.crm.comcast.objectrepositoryutility.Home;
import com.crm.comcast.objectrepositoryutility.Login;
import com.crm.comcast.objectrepositoryutility.OrganizationInfo;

import com.crm.comcast.objectrepositoryutility.Organizations;

/**
 * 
 * @author Deepak
 *
 */
@Listeners(com.crm.comcast.genericlib.ListenerIMP.class)
public class CreateContact extends BaseClass{

	@Test(groups = "smokeTest")
	public void cretecontact() throws Throwable {
		 
		 /*read test script data */ 
		 String contactNAme = elib.getExcelData("Contact", 1, 2)+ jLib.generateRandomNum();

		 /*step 2 : navigate to to contacts*/ 
		  Home hp = new Home(driver);
		  hp.getContactLnk().click();
		  
		 /*step 3 : navigate to create New Conatact Page*/
		  Contacts cp = new Contacts(driver);
		  cp.getCreateOrgImg().click();
		  
		 /*step 4 : create a Contact*/ 
		  CreateNewConatct cnc = new CreateNewConatct(driver);
		  cnc.createContact(contactNAme);
		  
		  /*verify the contact in contact info Page*/
		  ContactInfoPage cip = new ContactInfoPage(driver);
		  String actContactNAme = cip.getSuccessMsg().getText();
		  boolean flag = actContactNAme.contains(contactNAme);	
		  Assert.assertTrue(flag);  
		  
	
	}


	@Test(groups = "regressionTest")
	public void cretecontactwithORg() throws Throwable {
			
		 /*read test script data */ 
		 String orgNAme = elib.getExcelData("Contact", 4, 2)+ jLib.generateRandomNum();
		 String conatctNAme = elib.getExcelData("Contact", 4, 3)+ jLib.generateRandomNum();

		 /*step 2 : navigate to to contacts*/ 
		  Home hp = new Home(driver);
		  hp.getOrgLnk().click();
		  
		  /*step 3 : navigate to to ORG*/ 
		  Organizations op = new Organizations(driver);
		  op.getCreateOrgImg().click();
		  
		  /*step 3 : create Org*/
		  CreateNewOrganization cno = new CreateNewOrganization(driver);
		  cno.creatOrganization(orgNAme);
		  //verify
		  OrganizationInfo oip = new OrganizationInfo(driver);
		  Assert.assertTrue(oip.getSuccessFullMsg().getText().contains(orgNAme));

		 /*step 4 : navigate to to contacts*/ 
		  hp.getContactLnk().click();
		  
		 /*step 5 : navigate to create New Conatact Page*/
		  Contacts cp = new Contacts(driver);
		  cp.getCreateOrgImg().click();
		  
		 /*step 6 : create a Contact with Organization*/ 
		  CreateNewConatct cnc = new CreateNewConatct(driver);
		  cnc.createContact(conatctNAme, orgNAme);
		  
		  /*verify the contact in contact info Page*/
		  ContactInfoPage cip = new ContactInfoPage(driver);
		  String actContactNAme = cip.getSuccessMsg().getText();
		  boolean flag = actContactNAme.contains(conatctNAme);		  
		  Assert.assertTrue(false);

	}


}
