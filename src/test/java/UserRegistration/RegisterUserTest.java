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
		
		//login to OrangeHRM using admin credentials
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.loginWithAdminUser();
		
		//Navigate to Home Page
		HomePage homePage = new HomePage(getDriver());
		homePage.navigateToUserRegistrationPage();
		
		//Navigate to User Registration Page and enter user details
		UserRegistrationPage regPage = new UserRegistrationPage(getDriver());
		regPage.clickAddUser();
		regPage.enterUserDetails(user);
		
		//Validate User Registration is successful
		homePage.validateUserRegistrationIsSuccess();
	}

}
