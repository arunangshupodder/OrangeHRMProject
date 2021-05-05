package utils;

import org.json.JSONArray;

import base.BaseService;
import io.restassured.response.Response;
import objects.RegisteredUser;

public class ReqresData {
	
	public static RegisteredUser getUserForRegistration() {
		Response response = BaseService.get("https://reqres.in/api/users?page=2");
		return getUserFromUserList(7, response);
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
