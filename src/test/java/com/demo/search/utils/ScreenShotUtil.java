/**
 * 
 */
package com.demo.search.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class  ScreenShotUtil {
	
	public static void takeScreenShot(WebDriver driver, String casename) throws IOException {
		String ext = ".png";
		String dir = System.getProperty("user.dir");
		//String folderPath = dir + File.separator + "target" + File.separator + "surefire-reports" + File.separator + "images";
		String folderPath = dir + File.separator + "images";
		File folder = new File(folderPath);
		if(!folder.exists()){
			folder.mkdir();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(new Date());
		String filename = date+"_"+casename + ext;
		File fileout = new File(folderPath + File.separator+ filename);
		File filesrc =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(filesrc, fileout);
		
	}
}
