package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePages;
import utils.Config;

public class LoginPage extends BasePages {
	
	private WebDriver driver = null;
	private By userName = By.id("txtUsername");
	private By password = By.id("txtPassword");
	private By login = By.id("btnLogin");
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void loginWithAdminUser() throws IOException {
		setText(Config.getAdminUserName(), userName);
		setText(Config.getAdminUserPassword(), password);
		clickButton(login);
	}
	

}
