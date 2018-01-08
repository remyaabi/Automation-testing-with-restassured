package org.restassured.test.testscripts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.main.JsonSchema;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.path.json.JsonPath.from;

/**
 * Created by remya on 1/2/2018.
 */
public class TwitterStatus extends TestBase {
    static Logger logger = Logger.getLogger(TwitterApiTest.class.getName());
    Response response;
    ObjectMapper objectMapper = new ObjectMapper();
     String statusIdForGetStatus;
   Integer userId;
    @BeforeClass(alwaysRun = true)
    public void setup() throws IOException {
        super.loadData();
    }
    @Test(priority = 1)
    public void postStatusUpdate(){
        long time=System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date resultdate = new Date(time);
        String dateVal=sdf.format(resultdate);

        response = given().param("status", "Status update through rest assured " + dateVal).auth()
                .oauth(CONSUMERKEY, CONSUMERSECRET, ACCESSTOKEN, SECRETTOKEN).when().post(EndpointUrl.POST_STATUS.getResourcePath())
                .then().contentType(ContentType.JSON).statusCode(200).and().extract().response();
        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        System.out.println("response postStatusUpdate :" + response.asString());
        Object  statusId= jsonObject.get("id");
        statusIdForGetStatus=statusId.toString();
        System.out.println("statusIdForGetStatus in post status : "+ statusIdForGetStatus);
        response=given().param("id", statusIdForGetStatus).auth().oauth(CONSUMERKEY, CONSUMERSECRET, ACCESSTOKEN, SECRETTOKEN).when().get(EndpointUrl.GET_STATUS.getResourcePath())
                .then().contentType(ContentType.JSON).statusCode(200).extract().response();
        JSONObject jsonObject1 = new JSONObject(response.getBody().asString());

        String text=jsonObject1.getString("text");
        Assert.assertEquals(text,"Status update through rest assured " + dateVal);

    }

    @Test(priority = 2, dependsOnMethods="postStatusUpdate")
    public void getStatus(){
        System.out.println("statusIdForGetStatus : "+ statusIdForGetStatus);
        response=given().param("id", statusIdForGetStatus).auth().oauth(CONSUMERKEY, CONSUMERSECRET, ACCESSTOKEN, SECRETTOKEN).when().get(EndpointUrl.GET_STATUS.getResourcePath())
                .then().contentType(ContentType.JSON).statusCode(200).and().assertThat().body(matchesJsonSchemaInClasspath("getStatus.json")).and().extract().response();
        System.out.println("response getStatus :" + response.asString());
        JSONObject jsonObject = new JSONObject(response.getBody().asString());
       userId=jsonObject.getJSONObject("user").getInt("id");

    }

     @Test(priority =4)
    public void  getRecentTweetFavouriteList(){
        //param("user_id", userId).param("screen_name","brightcorona")
        response=given().auth().oauth(CONSUMERKEY, CONSUMERSECRET, ACCESSTOKEN, SECRETTOKEN).
                when().get(EndpointUrl.GET_RECENTTWEETS.getResourcePath()).then().contentType(ContentType.JSON).and().extract().response();
        JsonPath jsonpath=response.getBody().jsonPath();
        String response1=response.asString();
        List urlArrayLength=JsonPath.from(response1).getList("user.entities.url.urls");
        System.out.println("urlArrayLength :" +urlArrayLength.size());
        Assert.assertTrue(urlArrayLength.size()>0);
    }
    @Test
    public void getFriendsIds(){
        response=given().auth().oauth(CONSUMERKEY, CONSUMERSECRET, ACCESSTOKEN, SECRETTOKEN).
                when().get(EndpointUrl.GET_FRIENDSIDS.getResourcePath()).then().contentType(ContentType.JSON).and().extract().response();
        System.out.println("getFriendsIds response :" +response.asString());
        Assert.assertTrue((JsonPath.from(response.asString()).getList("ids").size()) >0);
    }
    @Test
    public void getFriendsList(){
        response=given().auth().oauth(CONSUMERKEY, CONSUMERSECRET, ACCESSTOKEN, SECRETTOKEN).
                when().get(EndpointUrl.GET_FRIENDSLIST.getResourcePath()).then().contentType(ContentType.JSON).and().extract().response();
        System.out.println("getFriendsIds response :" +response.asString());
        Assert.assertTrue((JsonPath.from(response.asString()).getList("users").size()) >0);
    }


    @Test
    public void standradTweetSearch(){
        String screen_name="bright_coder";
        response=given().param("q",screen_name).auth().oauth(CONSUMERKEY, CONSUMERSECRET, ACCESSTOKEN, SECRETTOKEN).when().get(EndpointUrl.STANDRAD_TWEETSEARCH.getResourcePath())
                .then().contentType(ContentType.JSON).extract().response();
        System.out.println("standardTweetSearch response :" +response.asString());
    }

}
