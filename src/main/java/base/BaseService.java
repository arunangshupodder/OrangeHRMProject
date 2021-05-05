package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BaseService {
	
	public static Response get(String url) {
		return RestAssured.get(url);
	}

}
