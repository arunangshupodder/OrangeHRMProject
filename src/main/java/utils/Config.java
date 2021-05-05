package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	private static String getDataFromProp(String key) throws IOException {
		FileReader reader=new FileReader("src\\test\\resources\\data.properties");  
	    Properties p=new Properties();  
	    p.load(reader);  
	    return p.getProperty(key);
	}
	
	public static String getOrangeHRMURL() throws IOException {
		return getDataFromProp("orangehrm.url");
	}
	
	public static String getReqresBaseURL() throws IOException {
		return getDataFromProp("reqres.base.url");
	}
	
	public static String getReqresUsersListURL() throws IOException {
		return getDataFromProp("reqres.users.list");
	}
	
	public static String getAdminUserName() throws IOException {
		return getDataFromProp("orangehrm.admin.username");
		
	}
	
	public static String getAdminUserPassword() throws IOException {
		return getDataFromProp("orangehrm.admin.password");
	}
	
	public static String getEmployeeName() throws IOException {
		return getDataFromProp("orangehrm.test.employeeName");
	}
	
	public static String getEmployeePassword() throws IOException {
		return getDataFromProp("orangehrm.test.password");
	}
	
	public static int getEmployeeIdFromReqres() throws IOException {
		return Integer.parseInt(getDataFromProp("reqres.user.id"));
	}

}
