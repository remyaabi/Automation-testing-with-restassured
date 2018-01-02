package org.restassured.test.testscripts;

public enum EndpointUrl {
	GET_ACCOUNTSETTING("/1.1/account/settings.json"), VERIFY_ACCOUNTSETTING(
			"/1.1/account/verify_credentials.json"), POST_ACCOUNTSETTING(
					"1.1/account/settings.json"), GET_FINDGEOLOCATION("1.1/geo/reverse_geocode.json");
	String resourcePath;

	EndpointUrl(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String getResourcePath() {
		return this.resourcePath;
	}
public String getResourcePath(String data){
	return this.resourcePath+data;
}
}
