package UserRegistration;

import java.io.IOException;

import org.junit.Test;

import base.Base;
import junit.framework.Assert;
import objects.RegisteredUser;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;
import utils.ReqresData;

public class RegisterUserTest extends Base {
	
	@SuppressWarnings("deprecation")
	@Test
	public void registerUserWithReqresData() throws IOException {
		//get user from Reqres api
		RegisteredUser user = ReqresData.getUserForRegistration();
		Assert.assertNotNull("No user found from api.", user);
		
		//login to OrangeHRM using adin credentials
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.loginWithAdminUser();
		
		HomePage homePage = new HomePage(getDriver());
		homePage.navigateToUserRegistrationPage();
		
		UserRegistrationPage regPage = new UserRegistrationPage(getDriver());
		regPage.clickAddUser();
		regPage.enterUserDetails(user);
		
		homePage.validateUserRegistrationIsSuccess();
		
	}

}
