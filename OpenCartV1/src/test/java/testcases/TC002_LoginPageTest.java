package testcases;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import testbase.BaseClass;

public class TC002_LoginPageTest extends BaseClass {
	

	
	@Test (groups= {"Regression","Master"})
	public void verifyLogin() {
		logger.info("***Starting TC_002_LoginPageTest***");
		
		HomePage hp = new HomePage(driver);
		LoginPage lp=new LoginPage(driver);
		MyAccountPage myAcc = new MyAccountPage(driver);
		
		hp.clickMyAccount();
		hp.clickLogin();
		logger.info("***Navigated to login page***");
		
		lp.enterEmail(p.getProperty("userName"));
		lp.enterPassword(p.getProperty("password"));
		lp.clickLogin();
		
		SoftAssert art = new SoftAssert();
		
		logger.info("***Validating LoginSuccess***");
		try {
			if(myAcc.isDisplayed()) {
				logger.info("***Test Passed***");
				art.assertTrue(true);
			}else {
				logger.info("***Test Failed***");
				art.assertTrue(false);
			}
			
		}catch (Exception e) {
			logger.info("***Test Failed***");
			art.fail();
			}
		
		logger.info("***Finished TC002_TestLoginPage***");
		art.assertAll();
		
	}

	
	

}
