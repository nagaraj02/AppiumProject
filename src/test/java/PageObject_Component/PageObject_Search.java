package PageObject_Component;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

public class PageObject_Search {
	
	public AppiumDriver driver;
	
	//2nd section
	@FindBy(id="com.bigbasket.mobileapp:id/action_search")
	public WebElement Search_btn;
	
	@FindBy(id="com.bigbasket.mobileapp:id/searchView")
	public WebElement Search_txtbox;
		
	@FindBy(id="com.bigbasket.mobileapp:id/txtEmptyMsg1")
	public WebElement Invalid_msg;
	
	@FindBy(id="com.bigbasket.mobileapp:id/txtProductCount")
	public WebElement Valid_msg;
	
	
	//1st section
	//initializing page factory
	public PageObject_Search(AppiumDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//3rd section
	
	public void Click_Search()
	{
		Search_btn.click();
		
	}
	
	public void Enter_Searchtxt(String Value)
	{
		Search_txtbox.sendKeys(Value + "\n");
		
	}
	
	public String getInvalidmsg()
	{
		return Invalid_msg.getText();
		
	}
	
	public String getValidmsg()
	{
		return Valid_msg.getText();
	}
	
	
	

}
