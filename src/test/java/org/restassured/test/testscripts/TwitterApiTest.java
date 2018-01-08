package org.restassured.test.testscripts;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restassured.test.pojo.Account;

import org.restassured.test.pojo.VerifyAccount;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class TwitterApiTest extends TestBase {
    static Logger logger = Logger.getLogger(TwitterApiTest.class.getName());
    Response response;
    ObjectMapper objectMapper = new ObjectMapper();
    VerifyAccount verifyAccount;

    @BeforeClass(alwaysRun = true)
    public void setup() throws IOException {
        super.loadData();
    }

    @Test
    public void getAccountSetting() {
        String screenNameExpected = "brightcorona";
        response = given().auth().oauth(CONSUMERKEY, CONSUMERSECRET, ACCESSTOKEN, SECRETTOKEN).when()
                .get(EndpointUrl.GET_ACCOUNTSETTING.getResourcePath()).then().assertThat().statusCode(200).extract().response();
        System.out.println("getAccountSetting : " + response.asString());

            try {
               Account  account = objectMapper.readValue(response.getBody().asString(), Account.class);
                System.out.println("account.getScreenName(): " + account.getScreenName());
                Assert.assertEquals(account.getScreenName(), screenNameExpected);
            } catch (IOException e) {
                e.printStackTrace();
                Assert.fail("Error thrown: " + e.getMessage());
            }

    }

    @Test
    public void getVerifyaccountCredentials() {
        String screenNameExpected = "brightcorona";
        String nameExpected = "Remya Jacob";
        response = given().auth().oauth(CONSUMERKEY, CONSUMERSECRET, ACCESSTOKEN, SECRETTOKEN).when()
                .get(EndpointUrl.VERIFY_ACCOUNTSETTING.getResourcePath()).then().extract().response();
        System.out.println("getVerifyaccountCredentials :" + response.asString());

            try {
                 verifyAccount = objectMapper.readValue(response.getBody().asString(), VerifyAccount.class);
                Assert.assertEquals(verifyAccount.getScreenName(), screenNameExpected);
                Assert.assertEquals(verifyAccount.getName(), nameExpected);
            } catch (IOException e) {
                e.printStackTrace();
                Assert.fail("Error thrown: " + e.getMessage());
            }

    }

	@Test
    public void postAccountSetting() {
		 response = given().param("lang", "es").auth()
				.oauth(CONSUMERKEY, CONSUMERSECRET, ACCESSTOKEN, SECRETTOKEN).when().post(EndpointUrl.POST_ACCOUNTSETTING.getResourcePath())
				.then().statusCode(200).and().extract().response();

        response = given().auth().oauth(CONSUMERKEY, CONSUMERSECRET, ACCESSTOKEN, SECRETTOKEN).when()
                .get(EndpointUrl.VERIFY_ACCOUNTSETTING.getResourcePath()).then().assertThat().statusCode(200).extract().response();
        System.out.println("response : " + response.asString());
        try {
             verifyAccount = objectMapper.readValue(response.getBody().asString(), VerifyAccount.class);
            Assert.assertEquals( verifyAccount.getLang(),"es");

            response = given().param("lang", "en").auth()
                    .oauth(CONSUMERKEY, CONSUMERSECRET, ACCESSTOKEN, SECRETTOKEN).when().post(EndpointUrl.POST_ACCOUNTSETTING.getResourcePath())
                    .then().statusCode(200).and().extract().response();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Error thrown: " + e.getMessage());
        }

	}
	
	@Test
	public void testGeoFindLocation() {
		String lat = "47.6740";
		String longitude = "-122.1215";
		 response = given().param("lat", lat).param("long", longitude)
				.auth().oauth(CONSUMERKEY, CONSUMERSECRET, ACCESSTOKEN, SECRETTOKEN)
				.when().get(EndpointUrl.GET_FINDGEOLOCATION.getResourcePath())
				.then().statusCode(200).and().extract().response();
		System.out.println("response : " + response.asString());
        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        JSONArray places = jsonObject.getJSONObject("result").getJSONArray("places");
        Assert.assertTrue(places.length() == 3);
        JSONObject city = places.getJSONObject(0);
        Assert.assertTrue(city.get("name").equals("Redmond"));
        JSONObject state = places.getJSONObject(1);
        Assert.assertTrue(state.get("name").equals("Washington"));
        JSONObject country = places.getJSONObject(2);
        Assert.assertTrue(country.get("name").equals("United States"));
    }

}
