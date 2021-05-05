package pages;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePages;
import junit.framework.Assert;
import objects.RegisteredUser;
import utils.Config;

public class UserRegistrationPage extends BasePages {
	
	private WebDriver driver = null;
	private By addUser = By.id("btnAdd");
	private By employeeName = By.id("systemUser_employeeName_empName");
	private By userName = By.id("systemUser_userName");
	private By password = By.id("systemUser_password");
	private By confirmPassword = By.id("systemUser_confirmPassword");
	private By saveButton = By.id("btnSave");
	private By employeeNotExistMessage = By.xpath("//span[text()='Employee does not exist']");
	
	public UserRegistrationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickAddUser() {
		clickButton(addUser);
	}
	
	public void enterUserDetails(RegisteredUser user) throws IOException {
		setText(Config.getEmployeeName(), employeeName);
		setText(user.getFirst_name().toLowerCase()+"."+user.getLast_name().toLowerCase()+"."+getRandomChars(), userName);
		setText(Config.getEmployeePassword(), password);
		setText(Config.getEmployeePassword(), confirmPassword);
		
		if (!verifyEmployeeNameNotAvailableMessageIsNotDisplayed()) {
			scrollElementToView(saveButton);
			clickButton(saveButton);
		} else {
			Assert.fail("User Name does not exist.");
		}	
	}
	
	public boolean verifyEmployeeNameNotAvailableMessageIsNotDisplayed() {
		try {
			fWait.withTimeout(Duration.ofSeconds(2))
			.until(ExpectedConditions.visibilityOfElementLocated(employeeNotExistMessage));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}
	
	private String getRandomChars() {
		Random random = new Random();
	    String generatedString = random.ints(97, 122).limit(5)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    return generatedString;
	}
}
