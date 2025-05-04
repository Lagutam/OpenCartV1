package testbase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass (groups= {"Sanity", "Regression","Master"})
	@Parameters({ "os", "browser" })
	public void setup(String os, String browser) throws IOException {
		
		//reading properties file
		FileReader file= new FileReader(".//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
				
		logger = LogManager.getLogger(this.getClass());
		
		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Invalid browser.");
			return;
		}

		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger.info("***Navigating to URL***");

		driver.get(p.getProperty("appURL")); //Reading properties.
		logger.info("***URL Loaded****");
		
		driver.manage().window().maximize();
	}

	@AfterClass(groups= {"Sanity", "Regression","Master"})
	public void tearDown() {
		driver.close();
	}

	@SuppressWarnings("deprecation")
	public String randomeString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	@SuppressWarnings("deprecation")
	public String randomeNumber() {
		String generatedString = RandomStringUtils.randomNumeric(10);
		return generatedString;
	}

	@SuppressWarnings("deprecation")
	public String randomAlphaNumeric() {
		String str = RandomStringUtils.randomAlphabetic(3);
		String num = RandomStringUtils.randomNumeric(3);

		return (str + "@" + num);
	}
	
	public String captureScreen(String fileName) throws IOException {
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
				
		//TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File sourceFile= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		String filePath = System.getProperty("user.dir")+"\\screenshots\\"+fileName+"_"+timeStamp+".png";
		File targetFile= new File(filePath);
		
		//sourceFile.renameTo(targetFile);
		FileUtils.copyFile(sourceFile, targetFile);
		
		return filePath;
	}

}
