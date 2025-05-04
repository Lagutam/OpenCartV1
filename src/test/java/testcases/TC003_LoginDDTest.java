package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testbase.BaseClass;
import utilities.DataProviders;
//import utilities.ExcelUtilities;

public class TC003_LoginDDTest extends BaseClass {
	
	@Test (dataProvider="LoginData", dataProviderClass= DataProviders.class, groups= {"Regression", "DataDriven"})
	public void performLoginDataDrivenTest(String email, String password, String exp) {
		
		logger.info("***Starting LoginDDTest***");
		HomePage hp= new HomePage(driver);
		
		hp.clickMyAccount();
		hp.clickLogin();
		
		logger.info("*** At Login Page***");
		LoginPage lp=new LoginPage(driver);
		lp.enterEmail(email);
		lp.enterPassword(password);
		lp.clickLogin();
		
		MyAccountPage acc = new MyAccountPage(driver);
		Boolean isDisplayed=acc.isDisplayed();
		
		try {		
		if(exp.toLowerCase().equals("valid")) {
			if(isDisplayed) {
				logger.info("***Test Passed");
				acc.clickLogout();
				Assert.assertTrue(true);
			} else {
				logger.info("***Test Failed***");
				Assert.assertTrue(false);
			}
		}else {
			if(isDisplayed) {
				logger.info("***Test Failed***");
				acc.clickLogout();
				Assert.assertTrue(false);
			}else {
				logger.info("***Test Passed");
				Assert.assertTrue(true);
			}
		}
		
	}catch(Exception e) {
			Assert.fail();
		}finally {
			logger.info("***Finished LoginDDTest***");
		}
		
		
		
		
		
	}
}
