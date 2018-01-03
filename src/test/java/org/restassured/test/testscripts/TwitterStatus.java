package org.restassured.test.testscripts;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * Created by remya on 1/2/2018.
 */
public class TwitterStatus extends TestBase {
    static Logger logger = Logger.getLogger(TwitterApiTest.class.getName());
    Response response;
    ObjectMapper objectMapper = new ObjectMapper();
     String statusIdForGetStatus;
    @BeforeClass(alwaysRun = true)
    public void setup() throws IOException {
        super.loadData();
    }
    @Test(priority = 0)
    public void postStatusUpdate(){
        response = given().param("status", "Status update through rest assured" + System.currentTimeMillis()).auth()
                .oauth(CONSUMERKEY, CONSUMERSECRET, ACCESSTOKEN, SECRETTOKEN).when().post(EndpointUrl.POST_STATUS.getResourcePath())
                .then().contentType(ContentType.JSON).statusCode(200).and().extract().response();
        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        System.out.println("response postStatusUpdate :" + response.asString());
        Object  statusId= jsonObject.get("id");
        statusIdForGetStatus=statusId.toString();
        System.out.println("statusIdForGetStatus in post status : "+ statusIdForGetStatus);

    }

    @Test(priority = 1, dependsOnMethods="postStatusUpdate")
    public void getStatus(){
        System.out.println("statusIdForGetStatus : "+ statusIdForGetStatus);
        response=given().param("id", statusIdForGetStatus).auth().oauth(CONSUMERKEY, CONSUMERSECRET, ACCESSTOKEN, SECRETTOKEN).when().get(EndpointUrl.GET_STATUS.getResourcePath())
                .then().contentType(ContentType.JSON).statusCode(200).and().assertThat().body(matchesJsonSchemaInClasspath("getStatus.json")).and().extract().response();
        System.out.println("response getStatus :" + response.asString());

    }

}
