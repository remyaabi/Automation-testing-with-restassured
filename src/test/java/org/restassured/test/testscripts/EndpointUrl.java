package org.restassured.test.testscripts;

public enum EndpointUrl {
	GET_ACCOUNTSETTING("/1.1/account/settings.json"),
	VERIFY_ACCOUNTSETTING("/1.1/account/verify_credentials.json"),
	POST_ACCOUNTSETTING("1.1/account/settings.json"),
	GET_FINDGEOLOCATION("1.1/geo/reverse_geocode.json"),
	POST_STATUS("1.1/statuses/update.json"),
	GET_STATUS("1.1/statuses/show.json"),
	GET_RECENTTWEETS("1.1/favorites/list.json"),
	POST_STATUSUNRETWEET("1.1/statuses/unretweet/:id.json"),
	STANDRAD_TWEETSEARCH("1.1/search/tweets.json"),
	GET_FRIENDSIDS("1.1/friends/ids.json"),
	GET_FRIENDSLIST("1.1/friends/list.json"),
	POST_DESTROY_STATUSBYID("1.1/statuses/destroy/:id.json");
	;
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
