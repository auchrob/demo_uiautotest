package com.demo.search.utils;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseCase {
	protected static WebDriver driver; 
	public static Logger logger = Logger.getLogger(BaseCase.class);
	protected ITestContext context;
	public static String dire;

	@BeforeTest
	public void beforeTest(ITestContext context){
		dire = System.getProperty("user.dir");
	}


//	@Parameters("browser")
	@BeforeMethod
	//public void beforeMethod(ITestContext context,String browser) throws Exception{
		public void beforeMethod(ITestContext context) throws Exception{
		//如果需要可以从testng.xml中读取浏览器配置
		String browser="chrome";
		if(browser.equalsIgnoreCase("firefox")){
			//启动预览器
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			this.context = context;
			this.context.setAttribute("driver", driver);
		}else if(browser.equalsIgnoreCase("chrome")){
			String webdirever = dire + File.separator + "res"+ File.separator + "web_dir"+ File.separator + "chromedriver.exe";
			System.setProperty("webdriver.chrome.driver",webdirever);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			this.context = context;
			this.context.setAttribute("driver", driver);
		}
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void stopTest()
	{
		 if(driver!=null){
	    	  driver.quit();
	      }else{
	    	  Assert.fail("driver没有获得对象,退出操作失败");
	      }
	}


}
