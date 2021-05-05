package base;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import groovyjarjarantlr.StringUtils;
import utils.Config;

public class Base {
	
	private WebDriver driver = null;
	private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	
	@Before
	public void startTest() throws IOException {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\driver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.get(Config.getOrangeHRMURL());
	}
	
	@After
	public void endTest() throws IOException {
		takeScreenshot();
		driver.close();
	}
	
	public void takeScreenshot() throws IOException {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File("target\\" + sdf3.format(timestamp) + ".png");
		FileUtils.copyFile(src, dest);
	}
	
	protected WebDriver getDriver() {
		return driver;
	}

}
