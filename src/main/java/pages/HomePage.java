package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import base.BasePages;
import junit.framework.Assert;

public class HomePage extends BasePages {
	
	private WebDriver driver = null;
	private By adminMenu = By.id("menu_admin_viewAdminModule");
	private By userManagementMenuItem = By.id("menu_admin_UserManagement");
	private By userMenuItem = By.id("menu_admin_viewSystemUsers");
	private By userRegSuccessMessage = By.xpath("//*[contains(text(),'Successfully Saved')]");
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void navigateToUserRegistrationPage() {
		moveToElement(adminMenu);
		moveToElement(userManagementMenuItem);
		clickButton(userMenuItem);
	}
	
	@SuppressWarnings("deprecation")
	public void validateUserRegistrationIsSuccess() {
		try {
			waitForContentInURL("/admin/viewSystemUsers");
		} catch (TimeoutException | NoSuchElementException e) {
			Assert.fail("User Not Created Successfully");
		}
	}

}
