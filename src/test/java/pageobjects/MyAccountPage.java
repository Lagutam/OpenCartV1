package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement msgHeading;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement btnLogout;
	
	@FindBy(xpath="//a[normalize-space()='Continue']")
	WebElement btnContinue;
	
	public boolean isDisplayed() {
		try {
		return msgHeading.isDisplayed();
		//throw new Exception("Exception message");
		}catch(Exception e) {
			return false;
		}
	}
		
	public void clickLogout() {
		try {
			btnLogout.click();
			btnContinue.click();
			} catch(Exception e) {
			
		}
	}
		
}
