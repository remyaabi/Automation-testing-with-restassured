package org.restassured.test.testscripts;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;


public class TestBase {
    static Logger logger = Logger.getLogger(TestBase.class.getName());
    public static Properties configprop;
    public static String CONSUMERKEY;
    public static String CONSUMERSECRET;
    public static String ACCESSTOKEN;
    public static String SECRETTOKEN;

    public void loadData() throws IOException {
        if (configprop == null) {
            configprop = new Properties();
            File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
            FileReader filereader = new FileReader(file);
            configprop.load(filereader);
            RestAssured.baseURI = configprop.getProperty("baseUrl");
            CONSUMERKEY = configprop.getProperty("CONSUMERKEY");
            CONSUMERSECRET  = configprop.getProperty("CONSUMERSECRET");
            ACCESSTOKEN = configprop.getProperty("ACCESSTOKEN");
            SECRETTOKEN = configprop.getProperty("SECRETTOKEN");
        }
    }



}
