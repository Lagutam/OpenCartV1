package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.RegistrationPage;
import testbase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test (groups= {"Sanity", "Master"})
	public void verify_account_registration()
	{
		try {
		logger.info("***Starting verify_account_registration() execution***");
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("***Navigating to Register Page***");
		hp.clickRegister();
		
		RegistrationPage regpage=new RegistrationPage(driver);
		logger.info("***Providing customer details***");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");// randomly generated the email
		regpage.setTelephone(randomeNumber());
		
		String password=randomAlphaNumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		String confmsg=regpage.getConfirmationMsg();
		//System.out.println(confmsg);
		logger.info("***Validating confirmation message***");

		if(confmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
			logger.info("***Test Passed***");
		}else {
			logger.info("***Test Failed***");
			logger.debug("Debug message: "+confmsg);
			Assert.assertTrue(false);

		}}
		catch(Exception e){
			Assert.fail();
		}
		
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
	}

}
