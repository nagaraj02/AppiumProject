package Generic_Component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import bsh.util.Util;

public class Base_Class {
	
	public static Process process;
	public static AppiumDriver driver;
	
	public static ExtentReports extentreport;
	public static ExtentTest extenttest;
		
	
	//Start server
	public static void Start_Server() throws IOException, InterruptedException
	{
		String Start_Server="D:\\Appium\\node.exe  D:\\Appium\\node_modules\\appium\\bin\\appium.js";
		
		 process = Runtime.getRuntime().exec(Start_Server);
		
		if(process!=null)
		{
			System.out.println("Appium Server is Started");
		}
		else
		{
			System.out.println("NOT able to Start the Server");
		}
		
		Thread.sleep(12000);
		
	}
	
	
	//Initiliaze app
	public static void Initialize_app() throws InterruptedException, IOException
	{
		//Launch app
		DesiredCapabilities capabilities= new DesiredCapabilities();
		
		//device details
		capabilities.setCapability("deviceName", "GT-I9300I");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4.4");
		
		//app details
		capabilities.setCapability("appPackage",Utility_Class.Reading_properties("Package_name"));
		capabilities.setCapability("appActivity",Utility_Class.Reading_properties("Activity_name"));
		
		//appium server details
		 driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		Thread.sleep(8000);
		
		
	}
	
	//Explicit wait
	
	public void Explicitwait(WebElement ele,long T1)
	{
		WebDriverWait wait= new WebDriverWait(driver, T1);
		wait.until(ExpectedConditions.visibilityOf(ele)).isDisplayed();
	}
	
	@BeforeSuite
	public void init_Setup()
	{
		Date date= new Date();
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		
		String Report = df.format(date);
		
		extentreport = new ExtentReports("D:\\Dec16_Project\\Report\\"+"Bigbasket"+"-"+Report+".html", false);
		
		
	}
	
	
	//Screenshot
	
	public String Snapshot1(String TC_ID,String Order) throws IOException 
	{
		
		Date date= new Date();
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		File file= new File(df.format(date)+".png");
		
		
		TakesScreenshot screenshot=(TakesScreenshot)driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File("D:\\Dec16_Project\\Screenshot\\"+TC_ID+"-"+Order+"-"+file));
		
		String path="D:\\Dec16_Project\\Screenshot\\"+TC_ID+"-"+Order+"-"+file;
		return path;
		
	}
	
	
	
	
	//Stop server
	
	public static void Stop_Server() throws InterruptedException
	{
		
		if(process!=null)
		{
			Thread.sleep(4000);
			
			process.destroy();						
			System.out.println("Stopped Appium Server");
			
			
		}
		
		Thread.sleep(4000);
		extentreport.endTest(extenttest);
		extentreport.flush();
		
		
	}

}
