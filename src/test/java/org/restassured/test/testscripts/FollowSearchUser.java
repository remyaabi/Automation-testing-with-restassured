package org.restassured.test.testscripts;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;

public class FollowSearchUser extends TestBase{
		static Logger logger = Logger.getLogger(TwitterApiTest.class.getName());

	@BeforeClass(alwaysRun = true)
	public void setup() throws IOException {
		super.loadData();

	}
	

}
