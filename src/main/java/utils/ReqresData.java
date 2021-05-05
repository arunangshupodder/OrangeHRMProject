package utils;

import java.io.IOException;

import org.json.JSONArray;

import base.BaseService;
import io.restassured.response.Response;
import junit.framework.Assert;
import objects.RegisteredUser;

public class ReqresData {
	
	public static RegisteredUser getUserForRegistration() throws IOException {
		Response response = BaseService.get(Config.getReqresBaseURL()+Config.getReqresUsersListURL());
		Assert.assertTrue("Error retrieving user list from api.", response.statusCode()==200);
		return getUserFromUserList(Config.getEmployeeIdFromReqres(), response);
	}
	
	private static RegisteredUser getUserFromUserList(int id, Response response) {
		JSONArray array = new JSONArray(response.jsonPath().getList("data"));
		RegisteredUser user = null;
		for (int index=0; index<array.length(); index++) {
			if (array.getJSONObject(index).getInt("id") == id) {
				user = new RegisteredUser();
				user.setId(array.getJSONObject(index).getInt("id"));
				user.setFirst_name(array.getJSONObject(index).getString("first_name"));
				user.setLast_name(array.getJSONObject(index).getString("last_name"));
				user.setEmail(array.getJSONObject(index).getString("email"));
				break;
			}
		}
		return user;
	}

}
