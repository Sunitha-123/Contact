package com.crm.comcast.genericlib;

import java.util.Date;
import java.util.Random;
/**
 * 
 * @author Deepak
 *
 */
public class JavaUtils {
	Date dateobj = new Date();
	/**
	 * used to generate Ramdom number  start from 0 to 10000
	 * @return
	 */
	public int  generateRandomNum() {
		Random random = new Random();
		 int randomInt = random.nextInt(10000);

		 return randomInt;
	}
	public String  getCurrentSystemDataAndTime() {
		
		String currentDate = dateobj.toString();
		return currentDate;
	}
	
	public String getCurrentYear() {
		String currentDate = dateobj.toString();
		String[] arr = currentDate.split(" ");
		return arr[5];
	}
    public String getMonth() {
		String currentDate = dateobj.toString();
		String[] arr = currentDate.split(" ");
		return arr[1];
	}
    public int getMonthInt() {
		dateobj.toString();
		
		return dateobj.getMonth();
	}
    public String getDate() {
    	String currentDate = dateobj.toString();
		String[] arr = currentDate.split(" ");
		return arr[2];
   	}
    
    public static String getDateAndTime() {
    	Date date=new Date();
    	return date.toString().replace(" ", "_").replace(":", "_");
    }

}
